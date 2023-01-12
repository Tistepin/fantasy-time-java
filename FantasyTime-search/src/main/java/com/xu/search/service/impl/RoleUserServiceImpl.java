package com.xu.search.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xu.common.utils.PageUtils;
import com.xu.common.utils.Query;

import com.xu.search.dao.RoleUserDao;
import com.xu.search.entity.RoleUserEntity;
import com.xu.search.service.RoleUserService;


@Service("roleUserService")
public class RoleUserServiceImpl extends ServiceImpl<RoleUserDao, RoleUserEntity> implements RoleUserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<RoleUserEntity> page = this.page(
                new Query<RoleUserEntity>().getPage(params),
                new QueryWrapper<RoleUserEntity>()
        );

        return new PageUtils(page);
    }

}