package com.xu.search.service.impl;

import com.xu.search.entity.WorksWatchHistoryEntity;
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

import com.xu.search.dao.WorksUploadDao;
import com.xu.search.entity.WorksUploadEntity;
import com.xu.search.service.WorksUploadService;


@Service("worksUploadService")
public class WorksUploadServiceImpl extends ServiceImpl<WorksUploadDao, WorksUploadEntity> implements WorksUploadService {

    @Autowired
    WorksService worksService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WorksUploadEntity> page = this.page(
                new Query<WorksUploadEntity>().getPage(params),
                new QueryWrapper<WorksUploadEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<WorksVo> getUserUploadWorks(Integer userID, Integer worksType, Integer page, Integer limit) {
        // 根据 用户ID和作品类型 查询前面limit条的的作品ID
        Map<String, Object> params =new HashMap<>();
        params.put("page", page.toString());
        params.put("limit", limit.toString());
        List<Long> WorksList = this.baseMapper.selectList(new QueryWrapper<WorksUploadEntity>().eq("user_id", userID)).stream().map(
                WorksUploadEntity::getWorksId
        ).collect(Collectors.toList());
        List<WorksVo> worksVos= worksService.getWorksPage(worksType,WorksList,page,limit);
        return worksVos;
    }

}