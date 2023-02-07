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
 * 漫画作品章节数量
 * 
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-09 15:30:20
 */
@Data
@TableName("ft_cartoon_works_details")
public class CartoonWorksDetailsEntity implements Serializable {
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
	 * 漫画章节ID 第几话
	 */
	private String cartoonChapterId;
	/**
	 * 漫画章节名字
	 */
	private String cartoonChapterName;
	/**
	 * 漫画页数
	 */
	private String cartoonPages;
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
	/**
	 * 逻辑删除状态 0-已删除 1-未删除
	 */
	private Long deleteStatus;
	/**
	 * 审核状态 0-审核中 1-审核成功 2-审核失败
	 */
	private Long reviewStatus;

}
