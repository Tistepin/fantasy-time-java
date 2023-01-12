package com.xu.security.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

///**
// * @Description:
// * @author: 徐國紀
// * @author: F3863479
// * @createTime: 2023-01-2023/1/2-上午 11:10
// */
//@Service
//public class LoginService implements UserDetailsService {
//    // user 加密后 $2a$10$avXnhON2l96j4jvXBnNCqOgH2Kc9G5TGYmVD02XzyRwVuDTgWQ6eO
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        String pwd ="$2a$10$avXnhON2l96j4jvXBnNCqOgH2Kc9G5TGYmVD02XzyRwVuDTgWQ6eO";
//        List<GrantedAuthority> auths =
//                AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_admin");
//        List<GrantedAuthority> grantedAuthorities =
//                AuthorityUtils.commaSeparatedStringToAuthorityList("admins,ROLE_管理员,ROLE_admin");
//        User user = new User(username, new BCryptPasswordEncoder().encode("user"), auths);
//        return user;
//    }
//}
