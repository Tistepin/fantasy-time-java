package com.xu.thirdparty.fegin;

import com.xu.common.utils.R;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description:
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-01-2023/1/11-上午 10:46
 */
@FeignClient(value = "fantasytime-works")
public interface WorksService {

    @GetMapping("/works/workschapterdetailedviewingcontent/getImageData")
    R getImageData(
            @RequestParam("WorksId") Integer WorksId,
            @RequestParam("WorksChapterId") Integer WorksChapterId,
            @RequestParam("ImageId") Integer ImageId);

    @GetMapping("/works/works/getWorksInfo")
    R getWorksInfo(@RequestParam("WorksId") Integer WorksId);

    @GetMapping("works/worksdefaultimage/info/{worksId}")
    public R getWorksDefaultImage(@PathVariable("worksId") Long worksId);
}
