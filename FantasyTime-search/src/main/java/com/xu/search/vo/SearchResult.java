package com.xu.search.vo;

import com.xu.common.TO.es.WorksEsModel;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-02-2023/2/3-下午 02:37
 */
@Data
public class SearchResult {

    /**
     * 查询到的所有商品信息
     */
    private List<WorksEsModel> Works;
    /**
     * 当前页码
     */
    private Integer pageNum;

    /**
     * 总记录数
     */
    private Long total;

    /**
     * 总页码
     */
    private Integer totalPages;

}
