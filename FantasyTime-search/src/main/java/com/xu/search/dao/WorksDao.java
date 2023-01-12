package com.xu.search.dao;

import com.xu.search.entity.WorksEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xu.search.vo.WorksInfoVo;
import com.xu.search.vo.WorksVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 作品信息
 * 
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-05 15:51:40
 */
@Mapper
public interface WorksDao extends BaseMapper<WorksEntity> {

    List<WorksEntity> getRecentlyUpdated(@Param("worksType") Integer worksType);

    List<WorksEntity> getClassicEnd(@Param("popularityIds") List<String> popularityIds);

    List<WorksEntity> getPopularSerial(@Param("area") Integer area, @Param("worksType") Integer worksType);

    List<WorksEntity> getLatestCreation(@Param("worksType") Integer worksType);

    List<WorksEntity> getRecommendedToday(@Param("worksType") Integer worksType, @Param("categoryIds") Set<String> set);

    List<WorksVo> getRecentlyUpdatedTop(@Param("worksType") Integer worksType, @Param("today") String today);

    List<WorksEntity> getDailyLeaderboardTop(@Param("worksType") Integer worksType, @Param("workList") ArrayList<String> workList);

    List<WorksEntity> getthreeDaysLeaderboardTop(@Param("worksType") Integer worksType);

    List<WorksEntity> getthisWeekLeaderboardTop(@Param("worksType") Integer worksType);

    List<WorksEntity> getthisMonthDaysLeaderboardTop(@Param("worksType") Integer worksType);

    WorksInfoVo selectWorksInfo(@Param("worksId") Integer worksId);

    List<WorksInfoVo> getRecommendedWorks(@Param("worksCategorys") String[] worksCategorys, @Param("worksType") Integer worksType);

    List<WorksVo> getWorksInfo(@Param("worksIds") List<Long> worksIds);
}
