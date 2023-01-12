package com.xu.security.filter;

import cn.hutool.core.util.ObjectUtil;
import com.xu.security.utils.TokenManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Description:  访问 令牌认证过滤器
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-01-2023/1/4-上午 10:03
 */
public class TokenAuthenticationFilter  extends BasicAuthenticationFilter {
    private TokenManager tokenManager;
    private StringRedisTemplate stringRedisTemplate;

    public TokenAuthenticationFilter(AuthenticationManager authManager, TokenManager tokenManager, StringRedisTemplate redisTemplate) {
        super(authManager);
        this.tokenManager = tokenManager;
        this.stringRedisTemplate = redisTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        // 1.获取当前成功认证的用户权限信息
        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
        // 2.判断   如果有权限信息 就放到上下文中
        if (ObjectUtil.isNotEmpty(authentication)) {
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authentication);
        }
        chain.doFilter(req,res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        //  从header里面获取token
        String token = request.getHeader("token");
        if (token!=null){
            // 从token里面获取用户名
            String username = tokenManager.getUserFromToken(token);
            // 用username从redis里面获取权限列表
            List<String> range = stringRedisTemplate.opsForList().range(username, -100, 100);
            Collection<GrantedAuthority> authorities =new ArrayList<>();
            for (String s : range) {
                SimpleGrantedAuthority ach=new SimpleGrantedAuthority(s);
                authorities.add(ach);
            }
            return new UsernamePasswordAuthenticationToken(username,token,authorities);
        }
        return null;
    }

}
