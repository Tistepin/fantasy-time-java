package com.xu.search.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Description:
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-02-2023/2/3-下午 02:28
 */
@Data
public class SearchParam {
    @NotEmpty(message = "作品类型不能为空")
    @ApiModelProperty(value = "作品类型",name = "worksType",example = "1",required = true,position = 1)
    private Integer worksType;
    /**
     * 作品名称
     */
    @ApiModelProperty(value = "作品名称",name = "worksName",example = "刃牙",required = false,position = 1)
    private String worksName;
    /**
     * 作品题材
     */
    @ApiModelProperty(value = "作品题材",name = "worksCategory",example = "1",required = false,position = 1)
    private String worksCategory;
    /**
     * 作品创作地区
     */
    @ApiModelProperty(value = "作品创作地区",name = "worksArea",example = "1",required = false,position = 1)
    private Integer worksArea;
    /**
     * 作品是否完结 1 未完结 2 已完结
     */
    @ApiModelProperty(value = "作品是否完结 1 未完结 2 已完结",name = "worksStatus",example = "1",required = false,position = 1)
    private Integer worksStatus;

    /**
     * 最新发布
     */
    @ApiModelProperty(value = "最新发布",name = "latestRelease",example = "false",required = false,position = 1)
    private boolean latestRelease;
    /**
     * 最近更新
     */
    @ApiModelProperty(value = "最近更新",name = "recentlyUpdated",example = "false",required = false,position = 1)
    private boolean recentlyUpdated;
    /**
     * 评分最高
     */
    @ApiModelProperty(value = "评分最高",name = "highestRated",example = "false",required = false,position = 1)
    private boolean highestRated;
    /**
     * 人气最旺
     */
    @ApiModelProperty(value = "人气最旺",name = "TheMostPopular",example = "false",required = false,position = 1)
    private boolean TheMostPopular;

    /**
     * 页码
     */
    private Integer pageNum = 1;
    /**
     * 页码
     */
    private Integer pageSize = 2;
}
