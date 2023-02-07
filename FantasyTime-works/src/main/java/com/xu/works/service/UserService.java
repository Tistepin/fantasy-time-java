package com.xu.works.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xu.common.utils.PageUtils;
import com.xu.works.entity.UserEntity;
import com.xu.works.to.UserTo;
import com.xu.works.to.userUpdateTo;

import javax.servlet.http.HttpServletRequest;
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

    UserEntity getUserEntity(HttpServletRequest request);

    void updateUser(userUpdateTo userUpdateTo);
}

