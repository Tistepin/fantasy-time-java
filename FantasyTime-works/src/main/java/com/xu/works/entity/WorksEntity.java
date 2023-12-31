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
 * 作品信息
 * 
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-09 15:30:20
 */
@Data
@TableName("ft_works")
public class WorksEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(type = IdType.AUTO)
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
	 * 创建人
	 */
	private String creator;
	/**
	 * 作品创作者
	 */
	private String worksCreator;
	/**
	 * 作品创作时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date worksCreateTime;
	/**
	 * 作品创作地区
	 */
	private String worksArea;
	/**
	 * 作品类型 1-漫画 2-小说 3 插图
	 */
	private Long worksType;
	/**
	 * 作品评分 10分满分
	 */
	private Float worksScore;
	/**
	 * 作品更新至多少
	 */
	private Long worksRenew;
	/**
	 * 作品人气 用户阅读10章以上为1
	 */
	private Long worksPopularity;
	/**
	 * 作品描述
	 */
	private String worksDescribe;
	/**
	 * 作品分类
	 */
	private String worksCategory;
	/**
	 * 作品状态 1-更新中 2-完结
	 */
	private Integer worksStatus;
	/**
	 * 作品状态 1-可以看 2-不可以看
	 */
	private Integer status;
	/**
	 * 注册时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/**
	 * 修改时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date editTime;
	/**
	 * 逻辑删除状态 0-已删除 1-未删除
	 */
	private Long deleteStatus;
	/**
	 *审核状态 0-审核中 1-审核成功 2-审核失败
	 */
	private Long reviewStatus;
}
