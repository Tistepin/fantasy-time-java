package com.xu.search.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xu.common.utils.PageUtils;
import com.xu.search.entity.PopularityEntity;
import com.xu.search.to.worksPopularityTodayTo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 作品人气
 *
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-05 17:23:59
 */
public interface PopularityService extends IService<PopularityEntity> {

    PageUtils queryPage(Map<String, Object> params);


    List<String> getPopularityTop(Integer worksType);

    void saveWorksPopularity(Integer worksType, Integer worksId, Integer userId);

    Integer updateByWorksId(ArrayList<worksPopularityTodayTo> worksPopularityTodayTos);
}

