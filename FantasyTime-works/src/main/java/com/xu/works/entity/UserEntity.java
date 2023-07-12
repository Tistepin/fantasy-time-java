package com.xu.works.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 用户信息表
 * 
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-09 15:30:20
 */
@Data
@TableName("ft_user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(type = IdType.AUTO)
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
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
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
	 * 注册时间
	 */
	@ApiModelProperty(value = "注册时间",name = "createTime",example = "",required = false,position = 15)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/**
	 * 修改时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "修改时间",name = "editTime",example = "",required = false,position = 16)
	private Date editTime;
	/**
	 * 逻辑删除状态 0-已删除 1-未删除
	 */
	private Long deleteStatus;

}
