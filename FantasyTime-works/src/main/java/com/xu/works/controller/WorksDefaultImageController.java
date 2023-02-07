package com.xu.works.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.xu.works.entity.WorksDefaultImageEntity;
import com.xu.works.service.WorksDefaultImageService;
import com.xu.common.utils.PageUtils;
import com.xu.common.utils.R;



/**
 * 作品封面图片服务请求路径存储
 *
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-02-06 10:47:44
 */
@RestController
@RequestMapping("works/worksdefaultimage")
public class WorksDefaultImageController {
    @Autowired
    private WorksDefaultImageService worksDefaultImageService;



    /**
     * 信息
     */
    @GetMapping("/info/{worksId}")
    public R getWorksDefaultImage(@PathVariable("worksId") Long worksId){
		WorksDefaultImageEntity worksDefaultImage = worksDefaultImageService.getWorksDefaultImage(worksId);
        return R.ok().data("data", worksDefaultImage);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody WorksDefaultImageEntity worksDefaultImage){
		worksDefaultImageService.save(worksDefaultImage);

        return R.ok();
    }

}
