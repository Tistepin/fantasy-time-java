package com.xu.search.dao;

import com.xu.search.entity.RoleUserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户角色关联表
 * 
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-05 15:51:40
 */
@Mapper
public interface RoleUserDao extends BaseMapper<RoleUserEntity> {
	
}
