package com.xu.search.to;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

/**
 * 作品信息
 * 
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-09 15:30:20
 */
@Data
public class WorksTo implements Serializable {
	private static final long serialVersionUID = 1L;


	/**
	 * 作品名
	 */
	@NotEmpty(message = "作品名不能为空")
	private String worksName;
	/**
	 * 作品默认展示图片
	 */
	@NotEmpty(message = "作品默认展示图片不能为空")
	private String defaultImage;
	/**
	 * 作品创作者
	 */
	@NotEmpty(message = "作品创作者不能为空")
	private String worksCreator;
	/**
	 * 作品创作时间
	 */
	@NotEmpty(message = "作品创作时间不能为空")
	private Date worksCreateTime;
	/**
	 * 作品创作地区
	 */
	@NotEmpty(message = "作品创作地区不能为空")
	private String worksArea;
	/**
	 * 作品类型 1-漫画 2-小说
	 */
	@NotEmpty(message = "作品类型不能为空")
	private Long worksType;
	/**
	 * 作品描述
	 */
	@NotEmpty(message = "作品描述不能为空")
	private String worksDescribe;
	/**
	 * 作品题材
	 */
	@NotEmpty(message = "作品题材不能为空")
	private String worksCategory;
	/**
	 * 作品状态 1-更新中 2-完结
	 */
	@NotEmpty(message = "作品状态不能为空")
	private Integer worksStatus;

}
