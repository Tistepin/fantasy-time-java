package com.xu.works.feign;

import com.xu.common.TO.es.WorksEsModel;
import com.xu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/search/UpdateEs")
    R UpdateEs(@RequestBody WorksEsModel worksEsModels);

    // 删除es数据
    @DeleteMapping("/search/DeleteWorksUpErrorData")
    R DeleteWorksUpErrorData(@RequestParam(value = "WorksId") Long WorksId);

    // 获取指定Id的es数据
    @GetMapping("/search/getEsWorksUp")
    R GetEsWorks(@RequestParam(value = "WorksId") Long WorksId);

}
