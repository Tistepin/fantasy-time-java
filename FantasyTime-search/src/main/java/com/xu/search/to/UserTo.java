package com.xu.search.to;

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
     * 用户名
     */
    @NotEmpty(message = "用户名不能为空")
    private String username;
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
    /**
     * 性别
     */
    @NotEmpty(message = "性别不能为空")
    private Integer gender;
}
