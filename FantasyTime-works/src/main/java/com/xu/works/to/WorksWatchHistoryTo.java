package com.xu.works.to;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Description:
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-02-2023/2/13-上午 09:31
 */
@Data
public class WorksWatchHistoryTo {
    /**
     * 漫画ID
     */
    @NotEmpty(message = "worksId不能为空")
    private Long worksId;
    /**
     * 漫画章节ID 第几话
     */
    @NotEmpty(message = "cartoonChapterId不能为空")
    private Long cartoonChapterId;


    /**
     * 用户观看到第几章的的第几张图片
     */
    @NotEmpty(message = "worksHistoryViewingChapterImage不能为空")
    private Long worksHistoryViewingChapterImage;
}
