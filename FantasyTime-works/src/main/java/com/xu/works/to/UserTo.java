package com.xu.works.to;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Description: 前台传来的用户注册信息
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-01-2023/1/10-上午 10:18
 */
@Data
public class UserTo {
    /**
     * 密码
     */
    @NotEmpty(message = "密码不能为空")
    private String password;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 手机号码
     */
    @NotEmpty(message = "手机号码不能为空")
    private String phone;
}
