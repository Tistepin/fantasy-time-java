package com.xu.search.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xu.common.utils.PageUtils;
import com.xu.common.utils.Query;

import com.xu.search.dao.NovelWorksDetailsDao;
import com.xu.search.entity.NovelWorksDetailsEntity;
import com.xu.search.service.NovelWorksDetailsService;


@Service("novelWorksDetailsService")
public class NovelWorksDetailsServiceImpl extends ServiceImpl<NovelWorksDetailsDao, NovelWorksDetailsEntity> implements NovelWorksDetailsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<NovelWorksDetailsEntity> page = this.page(
                new Query<NovelWorksDetailsEntity>().getPage(params),
                new QueryWrapper<NovelWorksDetailsEntity>()
        );

        return new PageUtils(page);
    }

}