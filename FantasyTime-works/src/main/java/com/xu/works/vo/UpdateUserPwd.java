package com.xu.works.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-05-2023/5/11-下午 01:23
 */
@Data
public class UpdateUserPwd {
    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码",name = "phone",example = "",required = true,position = 5)
    private String phone;

    /**
     * 旧密码
     */
    @ApiModelProperty(value = "旧密码",name = "pwd1",example = "",required = true,position = 5)
    private String Pwd1;

    /**
     * 新密码
     */
    @ApiModelProperty(value = "新密码",name = "pwd2",example = "",required = true,position = 5)
    private String Pwd2;
}
