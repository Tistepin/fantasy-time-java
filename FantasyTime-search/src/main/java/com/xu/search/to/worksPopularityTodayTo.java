package com.xu.search.to;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.NotEmpty;

/**
 * @Description:
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-01-2023/1/7-下午 02:00
 */
@Data
@Alias("worksPopularityToday")
public class worksPopularityTodayTo {
    /**
     * worksID 作品id
     */
    @NotEmpty(message = "worksId不能为空")
    public String worksId;
    /**
     * 人气
     */
    public Double Popularity;
}
