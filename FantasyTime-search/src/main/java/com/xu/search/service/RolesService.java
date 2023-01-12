package com.xu.search.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xu.common.utils.PageUtils;
import com.xu.search.entity.RolesEntity;

import java.util.List;
import java.util.Map;

/**
 * 角色信息表
 *
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-05 15:51:40
 */
public interface RolesService extends IService<RolesEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<String> getRolesInfo(Long id);
}

