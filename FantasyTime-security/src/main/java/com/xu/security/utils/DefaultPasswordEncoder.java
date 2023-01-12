package com.xu.security.utils;

import com.xu.common.utils.MD5;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Description: 继承security密码接口 然后重写加密方式
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-01-2023/1/4-上午 09:40
 */
@Component
public class DefaultPasswordEncoder implements PasswordEncoder {

    String Test ="1";
    // 密码加密方法
    @Override
    public String encode(CharSequence rawPassword) {
        return MD5.encrypt(rawPassword.toString());
    }
    /**
     * 密码对比
     * false 则
     * @param rawPassword 前台传入的密码
     * @param encodedPassword 已经加密的密码
     * @return
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return Objects.equals(encodedPassword, MD5.encrypt(rawPassword.toString()));
    }
}
