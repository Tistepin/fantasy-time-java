package com.xu.common.TO.es;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description:
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-01-2023/1/30-下午 01:59
 */
@Data
public class WorksEsModel {
    /**
     * 作品id 不是自增的
     */
    private Long worksId;
    /**
     * 商品名稱
     */
    private String worksName;
    /**
     * 作品封面
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
    private String worksCreateTime;
    /**
     * 作品创作地区
     */
    private String worksArea;
    /**
     * 作品创作地区 NAME
     */
    private String worksAreaName;
    /**
     * 作品类型 1-漫画 2-小说
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
     * 作品分类 Name
     */
    private String worksCategoryName;
    /**
     * 作品状态 1-更新中 2-完结
     */
    private Integer worksStatus;
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
}
