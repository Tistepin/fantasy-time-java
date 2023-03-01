package com.xu.search.feign;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Description:
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-02-2023/2/24-下午 01:31
 */
@FeignClient("fantasytime-works")
public interface WorksFeignService {
}
