package com.xu.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xu.common.utils.R;
import com.xu.common.utils.ResponseUtil;
import com.xu.security.entity.SecurityUser;
import com.xu.security.entity.User;
import com.xu.security.utils.TokenManager;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 登录过滤器，继承UsernamePasswordAuthenticationFilter，对用户名密码进行登录校验
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-01-2023/1/4-上午 09:56
 */
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {
    private TokenManager tokenManager;

    private StringRedisTemplate stringRedisTemplate;
    // 身份验证管理器
    private AuthenticationManager authenticationManager;


    public TokenLoginFilter(AuthenticationManager authenticationManager, TokenManager tokenManager, StringRedisTemplate stringRedisTemplate) {
        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;
        this.stringRedisTemplate = stringRedisTemplate;
        // 是否仅post访问
        this.setPostOnly(false);
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/FT/acl/login","POST"));
    }
    // 1.获取表单提交过来的用户名和密码
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                    user.getPassword(),
                    new ArrayList<>()
            ));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.attemptAuthentication(request, response);
    }

    // 2.当你认证成功会调用的方法
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // 1.获取用户信息
        SecurityUser securityUser = (SecurityUser)authResult.getPrincipal();
        // 2.获取用户名创建Token
        String username = securityUser.getUsername();
        String token = tokenManager.createToken(username);
        //3.把用户名和用户权限列表放入redis
        Boolean aBoolean = stringRedisTemplate.hasKey(username);
        if (!aBoolean){
            stringRedisTemplate.opsForList().leftPushAll(username,securityUser.getPermissionValueList());
        }

        // 4.返回token
        ResponseUtil.out(response, R.ok().data("data",username));
        ResponseUtil.out(response, R.ok().data("FantasyTimetoken",token));

    }

    // 3.认知失败会调用的方法
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        ResponseUtil.out(response, R.error());
    }
}
