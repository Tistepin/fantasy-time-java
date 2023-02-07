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
 * 作品章节详细观看内容 例如小说第几章位置,漫画第一话的第一张图
 * 
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-11 10:58:30
 */
@Data
@TableName("ft_works_chapter_detailed_viewing_content")
public class WorksChapterDetailedViewingContentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 章节ID
	 */
	private Long worksChapterId;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 作品id
	 */
	private Long worksId;
	/**
	 * 该画画作品的该章节的第几个图片
	 */
	private Long imageId;
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
	/**
	 * 章节数据存储位置
	 */
	private String worksChapterLocation;

}
