package com.xu.works.service.impl;

import com.xu.works.entity.UserEntity;
import com.xu.works.service.RolesService;
import com.xu.works.service.UserService;
import com.xu.security.entity.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

/**
 * @Description:
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-01-2023/1/2-上午 11:10
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserService userService;
    @Autowired
    RolesService rolesService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查找用户数据
        UserEntity entity=userService.getUserInfo(username);
        // 根据用户数据查找权限
        List<String> RoleNames=rolesService.getRolesInfo(entity.getId());
        SecurityUser securityUser = new SecurityUser();
        com.xu.security.entity.User user = new com.xu.security.entity.User();
        user.setPassword(entity.getPassword());
        user.setUsername(username);
        securityUser.setCurrentUserInfo(user);
        securityUser.setPermissionValueList(RoleNames);
        return securityUser;
    }
}
