package com.xu.works.to;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @Description:
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-01-2023/1/10-下午 01:37
 */
@Data

public class CartoonWorksDetailsEntityTo {
    /**
     * 作品id
     */
    @NotEmpty(message = "作品id不能为空")
    private Long worksId;
    /**
     * 漫画章节ID 第几话
     */
    @NotEmpty(message = "章节ID表示第几话")
    private String cartoonChapterId;
    /**
     * 漫画章节名字
     */
    private String cartoonChapterName;
    /**
     * 漫画页数 自己算
     */
    private String cartoonPages;

    private List<String> worksChapterLocations;
}
