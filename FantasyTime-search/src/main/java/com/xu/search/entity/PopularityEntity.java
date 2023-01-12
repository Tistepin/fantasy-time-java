package com.xu.search.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 作品人气
 * 
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-09 15:30:20
 */
@Data
@TableName("ft_popularity")
public class PopularityEntity implements Serializable {
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
	 * 作品名称
	 */
	private String name;
	/**
	 * 作品分类 1-漫画 2-小说
	 */
	private Integer worksType;
	/**
	 * 作品状态 1-更新中 2-完结
	 */
	private Integer worksStatus;
	/**
	 * 总人气 观看十章 加一
	 */
	private Long worksPopularityCount;
	/**
	 * 今天的人气 每个用户每天看一章加一 第二天清零
	 */
	private Long worksPopularityToday;
	/**
	 * 三天的人气 每个用户每天看一章加一 第四天天清零
	 */
	private Long worksPopularityThreeDays;
	/**
	 * 本周的人气 每个用户每天看一章加一 下一周清零
	 */
	private Long worksPopularityThisweek;
	/**
	 * 本月的人气 每个用户每天看一章加一 下一月清零
	 */
	private Long worksPopularityThismonth;
	/**
	 * 逻辑删除 0-已删除 1-未删除
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
