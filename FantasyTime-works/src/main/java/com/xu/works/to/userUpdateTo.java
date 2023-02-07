package com.xu.works.to;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description:
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-02-2023/2/6-下午 02:46
 */
@Data
public class userUpdateTo {
    /**
     * id
     */
    @ApiModelProperty(value = "用户id",name = "id",example = "1",required = true,position = 1)
    private Long id;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名",name = "username",example = "",required = false,position = 2)
    private String username;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码",name = "password",example = "",required = false,position = 3)
    private String password;
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称",name = "nickname",example = "",required = false,position = 4)
    private String nickname;
    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码",name = "phone",example = "",required = false,position = 5)
    private String phone;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱",name = "email",example = "",required = false,position = 6)
    private String email;
    /**
     * 头像
     */
    @ApiModelProperty(value = "头像",name = "header",example = "",required = false,position = 7)
    private String header;
    /**
     * 性别
     */
    @ApiModelProperty(value = "性别",name = "gender",example = "",required = false,position = 8)
    private Integer gender;
    /**
     * 生日
     */
    @ApiModelProperty(value = "生日",name = "birth",example = "",required = false,position = 9)
    private Date birth;
    /**
     * 所在城市
     */
    @ApiModelProperty(value = "所在城市",name = "city",example = "",required = false,position = 10)
    private String city;
    /**
     * 职业
     */
    @ApiModelProperty(value = "职业",name = "job",example = "",required = false,position = 11)
    private String job;
    /**
     * 个性签名
     */
    @ApiModelProperty(value = "个性签名",name = "sign",example = "",required = false,position = 12)
    private String sign;
    /**
     * 用户来源
     */
    @ApiModelProperty(value = "用户来源",name = "sourceType",example = "",required = false,position = 13)
    private Integer sourceType;
    /**
     * 启用状态
     */
    @ApiModelProperty(value = "启用状态",name = "status",example = "1",required = true,position = 14)
    private Integer status;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间",name = "editTime",example = "",required = false,position = 16)
    private Date editTime;
}
