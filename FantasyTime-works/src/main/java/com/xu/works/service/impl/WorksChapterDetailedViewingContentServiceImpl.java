package com.xu.works.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xu.common.utils.PageUtils;
import com.xu.common.utils.Query;
import com.xu.works.dao.WorksChapterDetailedViewingContentDao;
import com.xu.works.entity.WorksChapterDetailedViewingContentEntity;
import com.xu.works.service.WorksChapterDetailedViewingContentService;
import com.xu.works.to.ReviewCartoonWorksTo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service("worksChapterDetailedViewingContentService")
public class WorksChapterDetailedViewingContentServiceImpl extends ServiceImpl<WorksChapterDetailedViewingContentDao, WorksChapterDetailedViewingContentEntity> implements WorksChapterDetailedViewingContentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WorksChapterDetailedViewingContentEntity> page = this.page(
                new Query<WorksChapterDetailedViewingContentEntity>().getPage(params),
                new QueryWrapper<WorksChapterDetailedViewingContentEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public WorksChapterDetailedViewingContentEntity getImageData(Integer worksId, Integer worksChapterId, Integer imageId) {
        QueryWrapper<WorksChapterDetailedViewingContentEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("works_id", worksId);
        queryWrapper.eq("works_chapter_id", worksChapterId);
        queryWrapper.eq("image_id", imageId);
        queryWrapper.eq("delete_status", 1);
        queryWrapper.eq("review_status", 1);
        return this.baseMapper.selectOne(queryWrapper);
    }

    @Override
    public PageUtils queryPage(Integer page, Integer limit) {
        Map<String, Object> params = new HashMap<>();
        params.put("page", page.toString());
        params.put("limit", limit.toString());
        IPage<WorksChapterDetailedViewingContentEntity> pages = this.page(
                new Query<WorksChapterDetailedViewingContentEntity>().getPage(params),
                new QueryWrapper<WorksChapterDetailedViewingContentEntity>()
        );

        return new PageUtils(pages);
    }

    @Override
    public void reviewCartoonWorks(ReviewCartoonWorksTo reviewCartoonWorksTo) {
        this.baseMapper.reviewCartoonWorksTo(reviewCartoonWorksTo);
    }

}