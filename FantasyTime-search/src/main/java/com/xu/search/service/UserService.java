package com.xu.search.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xu.common.utils.PageUtils;
import com.xu.search.entity.UserEntity;
import com.xu.search.to.UserTo;

import java.util.Map;

/**
 * 用户信息表
 *
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-05 15:51:40
 */
public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveUserInfo(UserTo userTou);

    UserEntity getUserInfo(String username);
}

