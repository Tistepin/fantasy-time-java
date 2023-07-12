package com.xu.works.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Description:
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-07-2023/7/10-上午 09:49
 */
@Data
public class WorksWatchHistoryVo {

    /**
     * id
     */
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
     * 用户观看到第几章的ID
     */
    private Long worksHistoryViewingChapterId;
    /**
     * 用户观看到第几章的的第几张图片
     */
    private Long worksHistoryViewingChapterImage;

    /**
     * 该章节的Image个数
     * */
    private String cartoonPages;
}
