package com.xu.works.feign;

import com.xu.common.TO.es.WorksEsModel;
import com.xu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Description:
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-01-2023/1/31-上午 10:34
 */
@FeignClient("fantasytime-search")
public interface SearchFeignService {
    @PostMapping("/search/WorksUp")
    R WorksUp(@RequestBody WorksEsModel worksEsModels);
}
