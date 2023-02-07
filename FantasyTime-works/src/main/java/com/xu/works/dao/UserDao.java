package com.xu.works.dao;

import com.xu.works.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xu.works.to.userUpdateTo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户信息表
 * 
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-05 15:51:40
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

    void updateUser(@Param("userUpdateTo") userUpdateTo userUpdateTo);
}
