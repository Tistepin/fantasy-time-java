package com.xu.search.service.impl;

import com.xu.search.service.WorksService;
import com.xu.search.vo.WorksVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xu.common.utils.PageUtils;
import com.xu.common.utils.Query;

import com.xu.search.dao.WorksWatchHistoryDao;
import com.xu.search.entity.WorksWatchHistoryEntity;
import com.xu.search.service.WorksWatchHistoryService;


@Service("worksWatchHistoryService")
public class WorksWatchHistoryServiceImpl extends ServiceImpl<WorksWatchHistoryDao, WorksWatchHistoryEntity> implements WorksWatchHistoryService {

    @Autowired
    WorksService worksService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WorksWatchHistoryEntity> page = this.page(
                new Query<WorksWatchHistoryEntity>().getPage(params),
                new QueryWrapper<WorksWatchHistoryEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * @Description 根据页面类型 去历史记录查询 所有作品中随机十本
     * @Author F3863479
     * @Date 2023/1/6 上午 11:29
     * @Params [worksType]
     * @return java.util.List<java.lang.String> worksIds
     *
     */
    @Override
    public List<String> getRecommendedTodayTop(Integer worksType) {
        return this.baseMapper.getRecommendedTodayTop(worksType);
    }


    /**
     * @Description 最近浏览 用户观看的历史数据
     * @Author F3863479
     * @Date 2023/1/10 上午 10:40
     * @Params [worksCategory, worksType]
     * @return java.util.List<com.xu.search.vo.WorksVo>
     *
     */
    @Override
    public List<WorksVo> getWorksRecentlyViewed(Integer UserID, Integer worksType, Integer page, Integer limit) {
        // 根据 用户ID和作品类型 查询前面limit条的的作品ID
        Map<String, Object> params =new HashMap<>();
        params.put("page", page.toString());
        params.put("limit", limit.toString());
        IPage<WorksWatchHistoryEntity> pages = this.page(
                new Query<WorksWatchHistoryEntity>().getPage(params),
                new QueryWrapper<WorksWatchHistoryEntity>().select("works_id").eq("works_type",worksType)
        );
        // 查询ID后再查该id数组的全部WorksVo数据
        List<Long> WorksIds = pages.getRecords().stream().map(WorksWatchHistoryEntity::getWorksId).collect(Collectors.toList());
        List<WorksVo> worksVos=null;
        if (WorksIds.size()!=0){
             worksVos= worksService.getWorksInfo(WorksIds);
        }
        return worksVos;
    }


}