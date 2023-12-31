package com.xu.works.dao;

import com.xu.works.entity.CartoonWorksDetailsEntity;
import com.xu.works.entity.WorksWatchHistoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;
import java.util.List;

/**
 * 观看历史记录
 * 
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-06 11:25:56
 */
@Mapper
@Resource
public interface WorksWatchHistoryDao extends BaseMapper<WorksWatchHistoryEntity> {

    List<String> getRecommendedTodayTop(@Param("worksType") Integer worksType);

    CartoonWorksDetailsEntity GetMaxWorksHistoryInfo(@Param("worksId") Long worksId);
}
