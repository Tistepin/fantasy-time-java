package com.xu.works.to;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @Description:
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-02-2023/2/1-下午 01:19
 */
@Data
@Alias("ReviewWorksTo")
public class ReviewWorksTo {
    /**
     * id
     */
    private Long worksId;
    /**
     *审核状态 0-审核中 1-审核成功 2-审核失败
     */
    private Long reviewStatus;
}
