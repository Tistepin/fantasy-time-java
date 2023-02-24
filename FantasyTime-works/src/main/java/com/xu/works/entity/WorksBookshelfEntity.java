package com.xu.works.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 书架
 * 
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-02-14 14:15:33
 */
@Data
@TableName("ft_works_bookshelf")
public class WorksBookshelfEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 作品id
	 */
	private Long worksId;
	/**
	 * 作品名
	 */
	private String worksName;
	/**
	 * 作品默认展示图片
	 */
	private String defaultImage;
	/**
	 * 作品更新至多少
	 */
	private Long worksRenew;
	/**
	 * 作品类型 1-漫画 2-小说
	 */
	private Long worksType;
	/**
	 * 作品创作者
	 */
	private String worksCreator;
	/**
	 * 作品更新时间
	 */
	private Date worksUpdateTime;
	/**
	 * 逻辑删除状态 0-删除 1-存在
	 */
	private Long deleteStatus;
	/**
	 * 注册时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date editTime;

}
