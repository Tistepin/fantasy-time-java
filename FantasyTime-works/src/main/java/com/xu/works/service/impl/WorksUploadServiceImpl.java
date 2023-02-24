package com.xu.works.service.impl;

import com.xu.works.entity.UserEntity;
import com.xu.works.service.UserService;
import com.xu.works.service.WorksService;
import com.xu.works.vo.WorksVo;
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

import com.xu.works.dao.WorksUploadDao;
import com.xu.works.entity.WorksUploadEntity;
import com.xu.works.service.WorksUploadService;

import javax.servlet.http.HttpServletRequest;


@Service("worksUploadService")
public class WorksUploadServiceImpl extends ServiceImpl<WorksUploadDao, WorksUploadEntity> implements WorksUploadService {

    @Autowired
    WorksService worksService;
    @Autowired
    UserService userService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WorksUploadEntity> page = this.page(
                new Query<WorksUploadEntity>().getPage(params),
                new QueryWrapper<WorksUploadEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<WorksVo> getUserUploadWorks(Integer worksType, Integer page, Integer limit, HttpServletRequest request) {
        // 获取用户ID
        UserEntity userEntity = userService.getUserEntity(request);
        Long userID = userEntity.getId();
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