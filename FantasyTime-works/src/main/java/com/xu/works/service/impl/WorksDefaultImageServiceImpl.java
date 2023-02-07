package com.xu.works.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xu.common.utils.PageUtils;
import com.xu.common.utils.Query;

import com.xu.works.dao.WorksDefaultImageDao;
import com.xu.works.entity.WorksDefaultImageEntity;
import com.xu.works.service.WorksDefaultImageService;


@Service("worksDefaultImageService")
public class WorksDefaultImageServiceImpl extends ServiceImpl<WorksDefaultImageDao, WorksDefaultImageEntity> implements WorksDefaultImageService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WorksDefaultImageEntity> page = this.page(
                new Query<WorksDefaultImageEntity>().getPage(params),
                new QueryWrapper<WorksDefaultImageEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public WorksDefaultImageEntity getWorksDefaultImage(Long worksId) {
        WorksDefaultImageEntity worksDefaultImageEntity = this.baseMapper.selectOne(new QueryWrapper<WorksDefaultImageEntity>().eq("works_id", worksId));
        return worksDefaultImageEntity;
    }

}