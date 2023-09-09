package com.xu.works.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xu.common.utils.PageUtils;
import com.xu.common.utils.Query;
import com.xu.works.dao.WorksWatchHistoryDao;
import com.xu.works.entity.CartoonWorksDetailsEntity;
import com.xu.works.entity.UserEntity;
import com.xu.works.entity.WorksEntity;
import com.xu.works.entity.WorksWatchHistoryEntity;
import com.xu.works.service.CartoonWorksDetailsService;
import com.xu.works.service.UserService;
import com.xu.works.service.WorksService;
import com.xu.works.service.WorksWatchHistoryService;
import com.xu.works.to.WorksHistoryTo;
import com.xu.works.to.WorksWatchHistoryTo;
import com.xu.works.vo.WorksInfoVo;
import com.xu.works.vo.WorksVo;
import com.xu.works.vo.WorksWatchHistoryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;


@Service("worksWatchHistoryService")
public class WorksWatchHistoryServiceImpl extends ServiceImpl<WorksWatchHistoryDao, WorksWatchHistoryEntity> implements WorksWatchHistoryService {

    @Autowired
    WorksService worksService;
    @Autowired
    UserService userService;
    @Autowired
    CartoonWorksDetailsService cartoonWorksDetailsService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WorksWatchHistoryEntity> page = this.page(
                new Query<WorksWatchHistoryEntity>().getPage(params),
                new QueryWrapper<WorksWatchHistoryEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * @return java.util.List<java.lang.String> worksIds
     * @Description 根据页面类型 去历史记录查询 所有作品中随机十本
     * @Author F3863479
     * @Date 2023/1/6 上午 11:29
     * @Params [worksType]
     */
    @Override
    public List<String> getRecommendedTodayTop(Integer worksType) {
        return this.baseMapper.getRecommendedTodayTop(worksType);
    }


    /**
     * @return java.util.List<com.xu.search.vo.WorksVo>
     * @Description 最近浏览 用户观看的历史数据
     * @Author F3863479
     * @Date 2023/1/10 上午 10:40
     * @Params [worksCategory, worksType]
     */
    @Override
    public List<WorksVo> getWorksRecentlyViewed(Integer UserID, Integer worksType, Integer page, Integer limit) {
        // 根据 用户ID和作品类型 查询前面limit条的的作品ID
        Map<String, Object> params = new HashMap<>();
        params.put("page", page.toString());
        params.put("limit", limit.toString());
        IPage<WorksWatchHistoryEntity> pages = this.page(
                new Query<WorksWatchHistoryEntity>().getPage(params),
                new QueryWrapper<WorksWatchHistoryEntity>().select("works_id").eq("works_type", worksType)
        );
        // 查询ID后再查该id数组的全部WorksVo数据
        List<Long> WorksIds = pages.getRecords().stream().map(WorksWatchHistoryEntity::getWorksId).collect(Collectors.toList());
        List<WorksVo> worksVos = null;
        if (WorksIds.size() != 0) {
            worksVos = worksService.getWorksInfo(WorksIds);
        }
        return worksVos;
    }

    @Override
    public void Record(WorksWatchHistoryTo worksWatchHistoryTo, HttpServletRequest request) {
        // 获取用户ID
        UserEntity userEntity = userService.getUserEntity(request);
        WorksWatchHistoryEntity worksWatchHistoryEntity1 = this.baseMapper.selectOne(new QueryWrapper<WorksWatchHistoryEntity>().eq("works_id", worksWatchHistoryTo.getWorksId()).eq("user_id",userEntity.getId()));
        // 获取章节信息
        Long cartoonChapterId = worksWatchHistoryTo.getCartoonChapterId();
        CartoonWorksDetailsEntity cartoonWorksDetailsEntity = cartoonWorksDetailsService.getById(cartoonChapterId);
        if (worksWatchHistoryEntity1 != null) {
            worksWatchHistoryEntity1.setWorksHistoryViewingChapter(Long.valueOf(cartoonWorksDetailsEntity.getCartoonChapterId()));
            worksWatchHistoryEntity1.setWorksHistoryViewingChapterId(cartoonChapterId);
            worksWatchHistoryEntity1.setWorksHistoryViewingChapterImage(worksWatchHistoryTo.getWorksHistoryViewingChapterImage());
            long l = System.currentTimeMillis();
            worksWatchHistoryEntity1.setEditTime(new Date(l));
            this.baseMapper.updateById(worksWatchHistoryEntity1);
        } else {
            // 获取用户ID
            Long UserId = userEntity.getId();
            // 获取作品实体类
            Long worksId = worksWatchHistoryTo.getWorksId();
            WorksInfoVo worksInfo = worksService.getWorksInfo(worksId.intValue());
            WorksWatchHistoryEntity worksWatchHistoryEntity = new WorksWatchHistoryEntity();
            worksWatchHistoryEntity.setUserId(UserId);
            BeanUtils.copyProperties(worksInfo, worksWatchHistoryEntity);

            worksWatchHistoryEntity.setWorksHistoryViewingChapter(Long.valueOf(cartoonWorksDetailsEntity.getCartoonChapterId()));
            worksWatchHistoryEntity.setWorksHistoryViewingChapterId(cartoonChapterId);
            worksWatchHistoryEntity.setWorksHistoryViewingChapterImage(worksWatchHistoryTo.getWorksHistoryViewingChapterImage());
            worksWatchHistoryEntity.setEditTime(null);
            this.baseMapper.insert(worksWatchHistoryEntity);
        }

    }

    @Override
    public List<WorksHistoryTo> GetWorksWatchHistory(HttpServletRequest request) {
        // 获取用户ID
        UserEntity userEntity = userService.getUserEntity(request);
        Long UserId = userEntity.getId();
        List<WorksWatchHistoryEntity> WorksWatchHistoryEntityS = this.baseMapper.selectList(new QueryWrapper<WorksWatchHistoryEntity>().eq("user_id", UserId));
        // 创建返回对象
        List<WorksHistoryTo> collect = WorksWatchHistoryEntityS.stream().map(item -> {
            WorksHistoryTo worksHistoryTo = new WorksHistoryTo();
            BeanUtils.copyProperties(item, worksHistoryTo);
            return worksHistoryTo;
        }).collect(Collectors.toList());
        // 获取作品最后一次的更新信息
        List<WorksHistoryTo> collect1 = collect.stream().peek(item -> {
            // 查询并存储
            CartoonWorksDetailsEntity cartoonWorksDetailsEntities = this.baseMapper.GetMaxWorksHistoryInfo(item.getWorksId());
            WorksEntity worksEntity = worksService.getById(item.getWorksId());
            item.setCreateTime(cartoonWorksDetailsEntities.getCreateTime());
            item.setLatestChapter(cartoonWorksDetailsEntities.getCartoonChapterId());
            item.setWorksCreator(worksEntity.getWorksCreator());
        }).collect(Collectors.toList());

        return collect1;
    }

    @Override
    public WorksWatchHistoryVo GetRecordInfo(Integer worksId, HttpServletRequest request) {
        UserEntity userEntity = userService.getUserEntity(request);
        Long UserId = userEntity.getId();
        WorksWatchHistoryEntity worksWatchHistoryEntity =
                this.baseMapper.selectOne(new QueryWrapper<WorksWatchHistoryEntity>().eq("works_id", worksId).eq("user_id", UserId));
        WorksWatchHistoryVo worksWatchHistoryVo=new WorksWatchHistoryVo();
        if (worksWatchHistoryEntity==null){
            return worksWatchHistoryVo;
        }
        BeanUtils.copyProperties(worksWatchHistoryEntity,worksWatchHistoryVo);
        // 获取页数
        String cartoonPages = cartoonWorksDetailsService.getById(worksWatchHistoryVo.getWorksHistoryViewingChapterId()).getCartoonPages();
        worksWatchHistoryVo.setCartoonPages(cartoonPages);
        return worksWatchHistoryVo;
    }


}