package com.xu.search.service.impl;

import com.xu.search.entity.RoleUserEntity;
import com.xu.search.service.RoleUserService;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xu.common.utils.PageUtils;
import com.xu.common.utils.Query;

import com.xu.search.dao.RolesDao;
import com.xu.search.entity.RolesEntity;
import com.xu.search.service.RolesService;


@Service("rolesService")
public class RolesServiceImpl extends ServiceImpl<RolesDao, RolesEntity> implements RolesService {
    @Autowired
    RoleUserService roleUserService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<RolesEntity> page = this.page(
                new Query<RolesEntity>().getPage(params),
                new QueryWrapper<RolesEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * @Description 查询该用户的所有权限
     * @Author F3863479
     * @Date 2023/1/10 下午 02:19
     * @Params [id]
     * @return java.lang.String
     *
     */
    @Override
    public List<String> getRolesInfo(Long id) {
        // 查找该用户在权限关系表中的关联的所有权限
        List<Long> rids = roleUserService.list(new QueryWrapper<RoleUserEntity>().eq("uid", id)).stream().map(
                RoleUserEntity::getRid
        ).collect(Collectors.toList());
        // 根据Rids去roles表中查出全部name
        List<String> Role = this.baseMapper.selectList(new QueryWrapper<RolesEntity>().in("id", rids)).stream().map(
                RolesEntity::getRole

        ).collect(Collectors.toList());
        return Role;
    }

}