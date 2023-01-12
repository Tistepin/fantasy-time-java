package com.xu.search.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xu.common.utils.PageUtils;
import com.xu.search.entity.RoleUserEntity;

import java.util.Map;

/**
 * 用户角色关联表
 *
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-05 15:51:40
 */
public interface RoleUserService extends IService<RoleUserEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

