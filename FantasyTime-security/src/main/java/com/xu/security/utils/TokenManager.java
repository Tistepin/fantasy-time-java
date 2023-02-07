package com.xu.security.utils;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description:
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-01-2023/1/4-上午 09:50
 */
@Component
public class TokenManager {

    /**
     * token 过期时间
     */
    private long tokenExpiration = 24*60*60*1000;
    /**
     * token 签名密钥
     */
    private String tokenSignKey = "FantasyTime-xu";

    // 1.使用jwts根据用户名生成token
    public String createToken(String username){
        return Jwts.builder()
                // 设置主题
                .setSubject(username)
                // 设置过期时间
                .setExpiration(new Date(System.currentTimeMillis()+tokenExpiration))
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                .compressWith(CompressionCodecs.GZIP).compact();
    }

    // 2.根据Token获取用户信息Name
    public String getUserFromToken(String token) {
        return Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token).getBody().getSubject();
    }
    public void removeToken(String token) {
        //jwttoken无需删除，
    }

}
