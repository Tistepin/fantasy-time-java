package com.xu.works;

import com.alibaba.fastjson.JSONObject;
import com.xu.common.utils.MD5;
import com.xu.works.constant.WorksEnum;
import com.xu.works.dao.WorksDao;
import com.xu.works.service.PopularityService;
import com.xu.works.vo.WorksVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description:
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-01-2023/1/5-上午 10:24
 */
@SpringBootTest
public class FantasyTimeSearchTest {
    @Autowired
    WorksDao worksDao;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate<String,String> redisTemplate;
    @Autowired
    PopularityService popularityService;
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



}
