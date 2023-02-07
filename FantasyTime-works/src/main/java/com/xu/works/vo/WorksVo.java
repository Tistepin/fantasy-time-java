package com.xu.works.vo;

import lombok.Data;

/**
 * @Description:
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-01-2023/1/6-上午 09:29
 */
@Data
public class WorksVo {
    /**
     * id
     */
    private Long worksId;
    /**
     * 作品名
     */
    private String worksName;
    /**
     * 作品创作者
     */
    private String worksCreator;
    /**
     * 作品默认展示图片
     */
    private String defaultImage;
    /**
     * 作品更新至多少
     */
    private String worksRenew;
    /**
     * 作品人气 用户阅读10章以上为1
     */
    private Integer worksPopularity;
    /**
     * 作品分类 1-漫画 2-小说
     */
    private Integer worksSort;
    /**
     * 作品状态 1-更新中 2-完结
     */
    private Integer worksStatus;
    /**
     * 作品分类
     */
    private String worksCategory;
    /**
     * 作品分类
     */
    private String worksDescribe;
}
