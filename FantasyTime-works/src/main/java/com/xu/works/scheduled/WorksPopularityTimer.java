package com.xu.works.scheduled;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xu.works.constant.WorksEnum;
import com.xu.works.entity.PopularityEntity;
import com.xu.works.service.PopularityService;
import com.xu.works.vo.WorksVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 定时任务
 * 1.EnableScheduling 开启定时任务
 * 2.@Scheduled开启一个定时任务
 * <p>
 * 异步任务
 * 1.@EnableAsync开启异步任务
 * 2.@Async 给希望异步执行的方法标注上
 * 3.自动配置类 TaskExecutionAutoConfiguration  配置 TaskExecutionProperties
 *
 * @Description: 排行榜每日数据更新
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-01-2023/1/7-下午 01:04
 */
@EnableAsync
@EnableScheduling
@Service
public class WorksPopularityTimer {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Resource
    PopularityService popularityService;
    /**
     * 1.Spring中6位组成 不允许有第七位年
     * 2.Spring 周的位置中 1-7 代表周一到周日  不是 周日到周六
     * 3.定时任务不应该阻塞
     * 1.可以让业务以异步的方式提交到线程池
     * 2.支持定时任务线程池 通过设置 TaskSchedulingProperties 该改变
     * spring:
     * task:
     * scheduling:
     * pool:
     * size: 5
     * 3.定时任务异步执行
     * <p>
     * 解决: 使用异步+定时任务来完成定时任务不阻塞的功能
     */

    /**
     * 功能描述 :
     * 每天的凌晨零点 更新人气表的 三日  本周  本月
     * 从redis中获取昨天的日排行榜的人气数据 然后统一给 三日  本周  本月加上人气
     * 因为每次添加作品的时候都会在作品表和人气表都新增一条数据不用担心id不存在
     */
    @Async
    @Scheduled(cron = "0 0 0 * * ?") // 每天执行一次 每天的凌晨零点
    @Transactional
    public void leaderboardUpdateDays() {
        // 获取今日排行榜的redis数据
        BoundZSetOperations<String, String> works_popularity_today_mh_ZSet = stringRedisTemplate.boundZSetOps(WorksEnum.Works_mh_Enum.works_popularity_today_mh.getMsg());
        // 获取zset 今日漫画人气全部是数据 包含worksId 和 人气值
        Set<ZSetOperations.TypedTuple<String>> typedTuples = works_popularity_today_mh_ZSet.reverseRangeWithScores(0, -1);
        // 没有今日人气则不需要更新
        if (typedTuples.size() != 0) {
            // 获取数据集合
            List<PopularityEntity> popularityEntities = getPopularityEntitys(typedTuples);
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

    // 数据转换人气增加
    public List<PopularityEntity> getPopularityEntitys(Set<ZSetOperations.TypedTuple<String>> typedTuples) {
        // 暂时存储数据
        LinkedHashMap<String, Long> stringStringLinkedHashMap = new LinkedHashMap<>();
        // 循环收集数据
        ArrayList<String> worksId = new ArrayList<>();
        for (ZSetOperations.TypedTuple<String> typedTuple : typedTuples) {
            worksId.add(typedTuple.getValue());
            stringStringLinkedHashMap.put(typedTuple.getValue(), typedTuple.getScore().longValue());
        }
        List<PopularityEntity> popularityEntities = popularityService.list(new QueryWrapper<PopularityEntity>().in("works_id", worksId));
        for (PopularityEntity popularityEntity : popularityEntities) {
            Long worksId1 = popularityEntity.getWorksId();
            Long TodayPopularity = stringStringLinkedHashMap.get(worksId1.toString());
            // 总人气增加
            popularityEntity.setWorksPopularityCount(popularityEntity.getWorksPopularityCount() + TodayPopularity);
            // 三天人气增加
            popularityEntity.setWorksPopularityThreeDays(popularityEntity.getWorksPopularityThreeDays() + TodayPopularity);
            // 本周人气增加
            popularityEntity.setWorksPopularityThisweek(popularityEntity.getWorksPopularityThisweek() + TodayPopularity);
            // 本月人气增加
            popularityEntity.setWorksPopularityThismonth(popularityEntity.getWorksPopularityThismonth() + TodayPopularity);
            long l = System.currentTimeMillis();
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
            popularityEntity.setEditTime(new Date(l));
        }
        return popularityEntities;
    }

    // 每三天执行一次
    @Async
    @Scheduled(cron = "0 0 0 3 * ?") // 每三天的凌晨0点
    @Transactional
    public void leaderboardUpdateThreeDays(){
        // 删除三天的人气数据 redis
        String s = stringRedisTemplate.opsForValue().get(WorksEnum.Works_mh_Enum.works_popularity_three_days_mh.getMsg());
        List<Long> threeDaysLeaderboardList = Objects.requireNonNull(JSONObject.parseArray(s, WorksVo.class)).stream().map(WorksVo::getWorksId).collect(Collectors.toList());
        stringRedisTemplate.delete(WorksEnum.Works_mh_Enum.works_popularity_three_days_mh.getMsg());
        // 获取数据集合

        List<PopularityEntity> popularityEntities =  popularityService.list(new QueryWrapper<PopularityEntity>().in("works_id", threeDaysLeaderboardList)).stream().peek(item->{
            item.setWorksPopularityThreeDays(0L);
        }).collect(Collectors.toList());
        // 获取全部的数据后去数据库更新数据

        boolean b = popularityService.updateBatchById(popularityEntities);
        if (b) System.out.println("3日操作成功");
    }

    // 每周一的凌晨
    @Async
    @Scheduled(cron = "0 0 0 ? * MON") // 每周一的凌晨
    @Transactional
    public void leaderboardUpdateThisWeek(){
        // 删除本周的人气数据 redis
        String s = stringRedisTemplate.opsForValue().get(WorksEnum.Works_mh_Enum.works_popularity_thisWeek_mh.getMsg());
        List<Long> threeDaysLeaderboardList = Objects.requireNonNull(JSONObject.parseArray(s, WorksVo.class)).stream().map(WorksVo::getWorksId).collect(Collectors.toList());
        stringRedisTemplate.delete(WorksEnum.Works_mh_Enum.works_popularity_thisWeek_mh.getMsg());
        // 获取数据集合

        List<PopularityEntity> popularityEntities =  popularityService.list(new QueryWrapper<PopularityEntity>().in("works_id", threeDaysLeaderboardList)).stream().peek(item->{
            item.setWorksPopularityThisweek(0L);
        }).collect(Collectors.toList());
        // 获取全部的数据后去数据库更新数据

        boolean b = popularityService.updateBatchById(popularityEntities);
        if (b) System.out.println("本周操作成功");
    }

    // 每月一号的凌晨
    @Async
    @Scheduled(cron = "0 0 0 1 * ?") // 每月一号的凌晨
    @Transactional
    public void leaderboardUpdateThisMonth(){
        // 删除本月的人气数据 redis
        String s = stringRedisTemplate.opsForValue().get(WorksEnum.Works_mh_Enum.works_popularity_thisMonth_mh.getMsg());
        List<Long> threeDaysLeaderboardList = Objects.requireNonNull(JSONObject.parseArray(s, WorksVo.class)).stream().map(WorksVo::getWorksId).collect(Collectors.toList());
        stringRedisTemplate.delete(WorksEnum.Works_mh_Enum.works_popularity_thisMonth_mh.getMsg());
        // 获取数据集合

        List<PopularityEntity> popularityEntities =  popularityService.list(new QueryWrapper<PopularityEntity>().in("works_id", threeDaysLeaderboardList)).stream().peek(item->{
            item.setWorksPopularityThismonth(0L);
        }).collect(Collectors.toList());
        // 获取全部的数据后去数据库更新数据

        boolean b = popularityService.updateBatchById(popularityEntities);
        if (b) System.out.println("本月操作成功");
    }
}
