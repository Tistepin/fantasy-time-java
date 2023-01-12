package com.xu.security.utils;

import com.xu.common.utils.R;
import com.xu.common.utils.ResponseUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:  登出业务逻辑类
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-01-2023/1/4-上午 09:43
 */
@Component
public class TokenLogoutHandler implements LogoutHandler {
    private TokenManager tokenManager;
    private StringRedisTemplate stringRedisTemplate;

    public TokenLogoutHandler(TokenManager tokenManager, StringRedisTemplate redisTemplate) {
        this.tokenManager = tokenManager;
        this.stringRedisTemplate = redisTemplate;
    }
    // 登出逻辑
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // 1.从请求头里获取token
        String token = request.getHeader("token");
        // 2.token不为空 移除 token 从redis删除token
        if (token != null) {
            //移除token
            tokenManager.removeToken(token);
            //清空当前用户缓存中的权限数据
            String userName = tokenManager.getUserFromToken(token);
            stringRedisTemplate.delete(userName);
        }
        ResponseUtil.out(response, R.ok());
    }
}
