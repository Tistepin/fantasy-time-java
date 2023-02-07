package com.xu.search.login;

import com.xu.security.entity.SecurityUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @Description:
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-01-2023/1/2-上午 11:10
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    //    @Autowired
//    UserService userService;
//    @Autowired
//    RolesService rolesService;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // 查找用户数据
//        UserEntity entity=userService.getUserInfo(username);
//        // 根据用户数据查找权限
//        List<String> RoleNames=rolesService.getRolesInfo(entity.getId());
//        SecurityUser securityUser = new SecurityUser();
//        com.xu.security.entity.User user = new com.xu.security.entity.User();
//        user.setPassword(entity.getPassword());
//        user.setUsername(username);
//        securityUser.setCurrentUserInfo(user);
//        securityUser.setPermissionValueList(RoleNames);
//        return securityUser;
//    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("admin");
        User user = new User(username, "123", Collections.singleton(grantedAuthority));
        return user;
    }
}
