package com.xu.works.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xu.common.utils.PageUtils;
import com.xu.common.utils.R;
import com.xu.works.entity.CartoonWorksDetailsEntity;
import com.xu.works.service.CartoonWorksDetailsService;
import com.xu.works.to.CartoonWorksDetailsEntityTo;
import com.xu.works.to.ReviewCartoonWorksTo;
import com.xu.works.to.ReviewWorksTo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 漫画作品章节数量
 *
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-05 15:51:40
 */
@RestController
@RequestMapping("works/cartoonworksdetails")
@Api(tags = "漫画作品章节数量")
public class CartoonWorksDetailsController {
    @Autowired
    private CartoonWorksDetailsService cartoonWorksDetailsService;


    @ApiOperation(value = "作品章节目录查询", notes = "作品章节目录查询")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "worksId", value = "作品ID", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true),
                    @ApiImplicitParam(name = "order", value = "升序0 降序1", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true)
            }
    )
    @ApiResponses({
            @ApiResponse(code = 20000, message = "請求成功", response = R.class)
    })
    @GetMapping("/getWorksChapterList")
    public R getWorksChapterList(@RequestParam Integer worksId,
                                 @RequestParam Integer order) {
        List<CartoonWorksDetailsEntity> worksDetailsEntities=cartoonWorksDetailsService.getWorksChapterList(worksId,order);
        return R.ok().data("data",worksDetailsEntities);
    }

    /**
     * @Description 上传作品的章节信息 以及保存图片位置信息
     * @Author F3863479
     * @Date 2023/1/10 下午 01:35
     * @Params [worksId]
     * @return com.xu.common.utils.R
     *
     */
    @ApiOperation(value = "上传作品数据", notes = "上传作品数据")
    @ApiImplicitParam(name = "cartoonWorksDetailsEntityTo", value = "上传章节数据To ", paramType = "body", required = true, dataType = "CartoonWorksDetailsEntityTo")
    @ApiResponses({
            @ApiResponse(code = 20000, message = "請求成功", response = R.class)
    })
    @PutMapping("/saveUploadWorskData")
    public R saveUploadChapterData(@RequestBody CartoonWorksDetailsEntityTo cartoonWorksDetailsEntityTo,
                                   HttpServletRequest request) {
        String s = cartoonWorksDetailsService.saveUploadChapterData(cartoonWorksDetailsEntityTo, request);
        if (s.equalsIgnoreCase("FAIL")){
            R error = R.error();
            error.setMessage("保存失败");
            return error;
        }
        return R.ok();
    }

    @ApiOperation(value = "作品的要审核的章节目录", notes = "作品的要审核的章节目录")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "page", value = "页", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true),
                    @ApiImplicitParam(name = "limit", value = "页面个数", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true),
                    @ApiImplicitParam(name = "reviewStatus", value = "审核状态 0-审核中 1-审核成功 2-审核失败", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true),
                    @ApiImplicitParam(name = "deleteStatus", value = "逻辑删除状态 0-已删除 1-未删除", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true)
            }
    )
    @ApiResponses({
            @ApiResponse(code = 20000, message = "請求成功", response = R.class)
    })
    @GetMapping("/getReviewList")
    public R getReviewList(Integer page,Integer limit,Integer reviewStatus,Integer deleteStatus){
        Page pages = (Page) PageHelper.startPage(page, limit);  //pageNum为页码，pageSize为页面大小
        List<ReviewCartoonWorksTo> cartoonWorksTos = cartoonWorksDetailsService.getReviewList(reviewStatus,deleteStatus);
        PageInfo<ReviewCartoonWorksTo> pageInfo = new PageInfo<ReviewCartoonWorksTo>(cartoonWorksTos);
        return R.ok().data("page", pageInfo);
    }


    /**
     * @Description 章节审核
     * @Author F3863479
     * @Date 2023/2/1 下午 01:18
     * @Params [worksTo, request]
     * @return com.xu.common.utils.R
     *
     */
    @ApiOperation(value = "章节审核", notes = "章节审核")
    @ApiImplicitParam(name = "reviewCartoonWorksTo", value = "章节审核 ", paramType = "body", required = true, dataType = "ReviewCartoonWorksTo")
    @ApiResponses({
            @ApiResponse(code = 20000, message = "請求成功", response = R.class)
    })
    @PostMapping("/review")
    public R review(@RequestBody ReviewCartoonWorksTo reviewCartoonWorksTo) {
        cartoonWorksDetailsService.review(reviewCartoonWorksTo);
        return R.ok();
    }


    // 获取审核章节文件为压缩文件
    @ApiOperation(value = "获取审核章节文件为压缩文件", notes = "获取审核章节文件为压缩文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "worksChapterId", value = "获取审核章节文件为压缩文件", paramType = "query", dataType = "Integer", defaultValue = "1"),
            @ApiImplicitParam(name = "zipFilePath", value = "存储路径", paramType = "query", dataType = "String", defaultValue = "1"),
    })
    @ApiResponses({
            @ApiResponse(code = 20000, message = "請求成功", response = R.class)
    })
    @GetMapping("getChapterWorksZip")
    public R getChapterWorksZip(String zipFilePath,Integer worksChapterId) {
        cartoonWorksDetailsService.getChapterWorksZip(worksChapterId,zipFilePath);
        return R.ok();
    }

}
