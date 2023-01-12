package com.xu.search.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 观看历史记录
 * 
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-09 15:30:20
 */
@Data
@TableName("ft_works_watch_history")
public class WorksWatchHistoryEntity implements Serializable {
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
	 * 用户观看到第几章
	 */
	private Long worksHistoryViewingChapter;
	/**
	 * 作品分类 1-漫画 2-小说
	 */
	private Integer worksType;
	/**
	 * 作品状态 1-更新中 2-完结
	 */
	private Integer worksStatus;
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
