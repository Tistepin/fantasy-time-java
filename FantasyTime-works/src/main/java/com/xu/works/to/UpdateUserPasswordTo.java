package com.xu.works.to;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-02-2023/2/7-上午 08:41
 */
@Data
public class UpdateUserPasswordTo {
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱",name = "email",example = "",required = false,position = 6)
    private String email;
    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码",name = "phone",example = "",required = true,position = 5)
    private String phone;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码",name = "password",example = "",required = false,position = 3)
    private String password;
}
