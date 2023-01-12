package com.xu.search.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xu.common.utils.PageUtils;
import com.xu.common.utils.Query;

import com.xu.search.dao.WorksChapterDetailedViewingContentDao;
import com.xu.search.entity.WorksChapterDetailedViewingContentEntity;
import com.xu.search.service.WorksChapterDetailedViewingContentService;


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
        queryWrapper.eq("works_id",worksId);
        queryWrapper.eq("works_chapter_id",worksChapterId);
        queryWrapper.eq("image_id",imageId);
        return this.baseMapper.selectOne(queryWrapper);
    }

}