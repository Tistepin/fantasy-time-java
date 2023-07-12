package com.xu.works.controller;

import com.xu.common.utils.PageUtils;
import com.xu.common.utils.R;
import com.xu.works.entity.WorksChapterDetailedViewingContentEntity;
import com.xu.works.service.WorksChapterDetailedViewingContentService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 作品章节详细观看内容 例如小说第几章位置,漫画第一话的第一张图
 *
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-10 14:34:10
 */
@RestController
@RequestMapping("works/workschapterdetailedviewingcontent")
@Api(tags = "作品章节详细观看内容 例如小说第几章位置,漫画第一话的第一张图")
public class WorksChapterDetailedViewingContentController {
    @Autowired
    private WorksChapterDetailedViewingContentService worksChapterDetailedViewingContentService;

    /**
     * @return com.xu.common.utils.R
     * @Description 获取漫画的指定章节的指定图片
     * @Author F3863479
     * @Date 2023/2/1 上午 08:19
     * @Params [WorksId, WorksChapterId, ImageId]
     */
    @GetMapping("/getImageData")
    public R getImageData(
            @RequestParam Integer WorksId,
            @RequestParam Integer WorksChapterId,
            @RequestParam Integer ImageId) {
        WorksChapterDetailedViewingContentEntity worksChapterDetailedViewingContentEntity =
                worksChapterDetailedViewingContentService.getImageData(WorksId, WorksChapterId, ImageId);
        return R.ok().data("data", worksChapterDetailedViewingContentEntity);
    }

    @ApiOperation(value = "审核作品数据", notes = "审核作品数据")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "page", value = "页", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true),
                    @ApiImplicitParam(name = "limit", value = "页面个数", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true)
            }
    )
    @ApiResponses({
            @ApiResponse(code = 20000, message = "請求成功", response = R.class)
    })
    @GetMapping("/list")
    public R list(Integer page, Integer limit) {
        PageUtils pages = worksChapterDetailedViewingContentService.queryPage(page, limit);

        return R.ok().data("page", pages);
    }
}
