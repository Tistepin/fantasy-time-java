package com.xu.works.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户关系表 谁是谁的好友 谁关注的谁
 * 
 * @author xuguoji
 * @email 2532878663
 * @date 2023-08-24 10:50:16
 */
@Data
@TableName("ft_contact")
public class ContactEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private Date createdAt;
	/**
	 * 
	 */
	private Date updatedAt;
	/**
	 * 
	 */
	private Date deletedAt;
	/**
	 * 谁的关系
	 */
	private Long ownerId;
	/**
	 * 对应谁 ID
	 */
	private Long targetId;
	/**
	 * 什么关系 1好友 2 群
	 */
	private Long type;
	/**
	 * 
	 */
	private String descInfo;

}
