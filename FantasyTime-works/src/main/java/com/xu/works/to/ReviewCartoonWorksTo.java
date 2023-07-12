package com.xu.works.to;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description:
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-02-2023/2/1-下午 03:54
 */
@Data
public class ReviewCartoonWorksTo {
    /**
     * id
     */
    private Long id;
    /**
     * 作品id
     */
    private Long worksId;
    /**
     * 漫画章节ID 第几话
     */
    private Long cartoonChapterId;
    /**
     * 漫画章节名字
     */
    private String cartoonChapterName;
    /**
     * 漫画页数
     */
    private String cartoonPages;
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
    /**
     * 逻辑删除状态 0-已删除 1-未删除
     */
    private Long deleteStatus;
    /**
     * 审核状态 0-审核中 1-审核成功 2-审核失败
     */
    private Long reviewStatus;
    /**
     * 作品Name
     */
    private String worksName;
}
