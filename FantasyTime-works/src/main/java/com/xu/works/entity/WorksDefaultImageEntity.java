package com.xu.works.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 作品封面图片服务请求路径存储
 * 
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-02-06 10:47:44
 */
@Data
@TableName("ft_works_default_image")
public class WorksDefaultImageEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 作品id
	 */
	private Long worksId;
	/**
	 * 图片服务请求数据位置
	 */
	private String worksDefaultImage;
	/**
	 * 审核状态 0-审核中 1-审核成功 2-审核失败
	 */
	private Long reviewStatus;
	/**
	 * 逻辑删除状态 0-删除 1-存在
	 */
	private Long deleteStatus;
	/**
	 * 注册时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/**
	 * 修改时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date editTime;

}
