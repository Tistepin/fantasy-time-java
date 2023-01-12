package com.xu.search.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xu.common.utils.PageUtils;
import com.xu.search.entity.WorksWatchHistoryEntity;
import com.xu.search.vo.WorksVo;

import java.util.List;
import java.util.Map;

/**
 * 观看历史记录
 *
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-06 11:25:56
 */
public interface WorksWatchHistoryService extends IService<WorksWatchHistoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<String> getRecommendedTodayTop(Integer worksType);


    List<WorksVo> getWorksRecentlyViewed(Integer userID, Integer worksType, Integer page, Integer limit);
}

