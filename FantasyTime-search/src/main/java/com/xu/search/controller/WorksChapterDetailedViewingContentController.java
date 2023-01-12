package com.xu.search.controller;

import java.util.Arrays;
import java.util.Map;

import com.xu.search.to.CartoonWorksDetailsEntityTo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.xu.search.entity.WorksChapterDetailedViewingContentEntity;
import com.xu.search.service.WorksChapterDetailedViewingContentService;
import com.xu.common.utils.PageUtils;
import com.xu.common.utils.R;

import javax.servlet.http.HttpServletRequest;


/**
 * 作品章节详细观看内容 例如小说第几章位置,漫画第一话的第一张图
 *
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-10 14:34:10
 */
@RestController
@RequestMapping("search/workschapterdetailedviewingcontent")
public class WorksChapterDetailedViewingContentController {
    @Autowired
    private WorksChapterDetailedViewingContentService worksChapterDetailedViewingContentService;

    @GetMapping("/getImageData")
    public R getImageData(
            @RequestParam Integer WorksId,
            @RequestParam Integer WorksChapterId,
            @RequestParam Integer ImageId){
       WorksChapterDetailedViewingContentEntity worksChapterDetailedViewingContentEntity=
               worksChapterDetailedViewingContentService.getImageData(WorksId,WorksChapterId,ImageId);
        return R.ok().data("data",worksChapterDetailedViewingContentEntity);
    }


}
