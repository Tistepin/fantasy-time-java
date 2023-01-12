package com.xu.search.dao;

import com.xu.search.entity.PopularityEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xu.search.to.worksPopularityTodayTo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * 作品人气
 * 
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-05 17:23:59
 */
@Mapper
public interface PopularityDao extends BaseMapper<PopularityEntity> {

    List<String> getPopularityTop(@Param("worksType") Integer worksType);

    List<String> getDailyLeaderboardTop(@Param("worksType") Integer worksType);

    List<String> getthreeDaysLeaderboard(@Param("worksType") Integer worksType);

    List<String> getthisWeekLeaderboard(@Param("worksType") Integer worksType);

    List<String> getthisMonthDaysLeaderboard(@Param("worksType") Integer worksType);

    Integer updateByWorksId(@Param("worksPopularityTodayTos") ArrayList<worksPopularityTodayTo> worksPopularityTodayTos);
}
