package com.xu.works.to;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * @Description: 返回历史观看信息
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-05-2023/5/12-上午 09:41
 */
@Data
public class WorksHistoryTo {

    /**
     * 漫画ID
     */
    @NotEmpty(message = "worksId不能为空")
    private Long worksId;
    /**
     * 作品名字
     */
    @NotEmpty(message = "作品名字不能为空")
    private String worksName;


    /**
     * 作品默认展示图片
     */
    private String defaultImage;

    /**
     * 漫画更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /**
     * 漫画最新章节
     */
    private Long latestChapter;


    /**
     * 作品创作者
     */
    private String worksCreator;
}
