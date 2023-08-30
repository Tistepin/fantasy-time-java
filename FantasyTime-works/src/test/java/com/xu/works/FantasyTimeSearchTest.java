package com.xu.works;

import cn.hutool.json.JSONUtil;
import com.xu.works.constant.WorksEnum;
import com.xu.common.constant.systemEnum;
import com.xu.works.dao.WorksDao;
import com.xu.works.entity.PopularityEntity;
import com.xu.works.entity.WorksEntity;
import com.xu.works.scheduled.WorksPopularityTimer;
import com.xu.works.service.PopularityService;
import com.xu.works.service.WorksService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.*;
import java.util.*;

/**
 * @Description:
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-01-2023/1/5-上午 10:24
 */
@SpringBootTest
public class FantasyTimeSearchTest {
    @Resource
    WorksDao worksDao;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate<String,String> redisTemplate;
    @Autowired
    PopularityService popularityService;
    @Autowired
    WorksService worksService;
    @Test
    void test(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("admin");
        strings.add("ROLE_管理员");
        stringRedisTemplate.opsForList().leftPushAll("user",strings.toString());
    }

    @Test
    void test2(){
//        stringRedisTemplate.opsForZSet().add("works","1",800);
//        stringRedisTemplate.opsForZSet().add("works","5",7000);
//        // 根据分数 Scores 倒序
//        Set<ZSetOperations.TypedTuple<String>> works = stringRedisTemplate.opsForZSet().reverseRangeWithScores("works", 0, -1);
//        System.out.println(works);
//        for (ZSetOperations.TypedTuple<String> work : works) {
//            String WorksID = work.getValue();
//            Double score = work.getScore();
//        }
//        BoundZSetOperations<String, String> works = stringRedisTemplate.boundZSetOps(WorksEnum.Works_mh_Enum.works_popularity_today_mh.getMsg());
//        works.incrementScore("1",1);
        BoundZSetOperations<String, String> works_popularity_today_mh_ZSet = stringRedisTemplate.boundZSetOps(WorksEnum.Works_mh_Enum.works_popularity_today_mh.getMsg());
        ScanOptions build = ScanOptions.scanOptions().count(100).build();
        Cursor<ZSetOperations.TypedTuple<String>> scan = works_popularity_today_mh_ZSet.scan(build);
        Set<ZSetOperations.TypedTuple<String>> typedTuples = works_popularity_today_mh_ZSet.rangeWithScores(0, -1);
        try {
            scan.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void test3(){
        String a="123";
        System.out.println(a.hashCode());
    }


    @Test
    public void testJavaBean(){
        WorksEntity worksEntity = worksDao.selectById(1);
        String UserToStr = JSONUtil.toJsonStr(
                worksEntity
        );
        //存入数据
        stringRedisTemplate.opsForValue().set("user:1",UserToStr);
    }

    @Autowired
    WorksPopularityTimer worksPopularityTimer;
    @Test
    void cs(){
        // 获取今日排行榜的redis数据
        BoundZSetOperations<String, String> works_popularity_today_mh_ZSet = stringRedisTemplate.boundZSetOps(WorksEnum.Works_mh_Enum.works_popularity_today_mh.getMsg());
        // 获取zset 今日漫画人气全部是数据 包含worksId 和 人气值
        Set<ZSetOperations.TypedTuple<String>> typedTuples = works_popularity_today_mh_ZSet.reverseRangeWithScores(0, -1);
        // 没有今日人气则不需要更新
        if (typedTuples.size() != 0) {
            // 获取数据集合
            List<PopularityEntity> popularityEntities = worksPopularityTimer.getPopularityEntitys(typedTuples);
            // 获取全部的数据后去数据库更新数据
            boolean b = popularityService.updateBatchById(popularityEntities);
            // 更新完成删除redis今日 三日 本周 本月 排行榜数据
            if (b) {
                stringRedisTemplate.delete(WorksEnum.Works_mh_Enum.works_popularity_today_mh.getMsg());
                stringRedisTemplate.delete(WorksEnum.Works_mh_Enum.works_popularity_three_days_mh.getMsg());
                stringRedisTemplate.delete(WorksEnum.Works_mh_Enum.works_popularity_thisWeek_mh.getMsg());
                stringRedisTemplate.delete(WorksEnum.Works_mh_Enum.works_popularity_thisMonth_mh.getMsg());
            }
        }
    }

    @Test
    public void testIp() throws UnknownHostException, SocketException {
        InetAddress localHost = InetAddress.getLocalHost();
        String hostAddress = localHost.getHostAddress();
        String hostName = localHost.getHostName();
        System.out.println("传统方式-----------hostAddress = " + hostAddress);
    }


    @Test
    void TestUSERNAME(){
        System.out.println(systemEnum.USERIP.getMsg());
        System.out.println(systemEnum.USERNAME.getMsg());


    }
}
