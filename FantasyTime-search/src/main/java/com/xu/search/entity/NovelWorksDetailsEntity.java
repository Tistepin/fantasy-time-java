package com.xu.search.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 小说作品章节数量
 * 
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-09 15:30:20
 */
@Data
@TableName("ft_novel_works_details")
public class NovelWorksDetailsEntity implements Serializable {
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
	 * 小说章节ID 第几章
	 */
	private String novelChapterId;
	/**
	 * 小说章节名字
	 */
	private String novelChapterName;
	/**
	 * 漫画页数
	 */
	private String novelPages;
	/**
	 * 注册时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date editTime;
	/**
	 * 逻辑删除状态 0-已删除 1-未删除
	 */
	private Long deleteStatus;

}
