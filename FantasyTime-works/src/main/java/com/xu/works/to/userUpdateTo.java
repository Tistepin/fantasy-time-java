package com.xu.works.to;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
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
     * 用户名
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ApiModelProperty(value = "用户名",name = "username",example = "",required = false,position = 2)
    private String username;

    /**
     * 昵称
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ApiModelProperty(value = "昵称",name = "nickname",example = "",required = false,position = 4)
    private String nickname;
    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码",name = "phone",example = "",required = true,position = 5)
    private String phone;
    /**
     * 邮箱
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ApiModelProperty(value = "邮箱",name = "email",example = "",required = false,position = 6)
    private String email;
    /**
     * 头像
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ApiModelProperty(value = "头像",name = "header",example = "",required = false,position = 7)
    private String header;
    /**
     * 性别
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ApiModelProperty(value = "性别",name = "gender",example = "",required = false,position = 8)
    private Integer gender;
    /**
     * 生日
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "生日",name = "birth",example = "",required = false,position = 9)
    private Date birth;
    /**
     * 所在城市
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ApiModelProperty(value = "所在城市",name = "city",example = "",required = false,position = 10)
    private String city;
    /**
     * 职业
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ApiModelProperty(value = "职业",name = "job",example = "",required = false,position = 11)
    private String job;
    /**
     * 个性签名
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ApiModelProperty(value = "个性签名",name = "sign",example = "",required = false,position = 12)
    private String sign;
}
