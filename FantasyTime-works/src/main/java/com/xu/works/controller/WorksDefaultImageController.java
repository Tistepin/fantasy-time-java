package com.xu.works.controller;

import java.util.Arrays;
import java.util.Map;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.xu.works.entity.WorksDefaultImageEntity;
import com.xu.works.service.WorksDefaultImageService;
import com.xu.common.utils.PageUtils;
import com.xu.common.utils.R;
import org.springframework.web.multipart.MultipartFile;


/**
 *
 *
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-02-06 10:47:44
 */
@RestController
@RequestMapping("works/worksdefaultimage")
@Api(tags = "作品封面图片服务请求路径存储")
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
    @PostMapping("/save")
    public R save( @RequestBody WorksDefaultImageEntity worksDefaultImageEntity){
		worksDefaultImageService.save(worksDefaultImageEntity);
        return R.ok();
    }

}
