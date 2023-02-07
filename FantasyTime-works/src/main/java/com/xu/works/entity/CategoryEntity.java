package com.xu.works.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * 作品类型
 * 
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-09 15:30:20
 */
@Data
@TableName("ft_category")
public class CategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 分类id
	 */
	@TableId(type = IdType.AUTO)
	private Long catId;
	/**
	 * 分类名称
	 */
	private String name;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 作品分类 1-漫画 2-小说
	 */
	private Integer worksType;
	/**
	 * 图标地址
	 */
	private String icon;
	/**
	 * 逻辑删除状态 0-已删除 1-未删除
	 */
	private Long deleteStatus;

}
