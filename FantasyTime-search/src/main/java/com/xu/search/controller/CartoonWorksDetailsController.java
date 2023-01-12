package com.xu.search.controller;

import com.xu.common.utils.R;
import com.xu.search.entity.CartoonWorksDetailsEntity;
import com.xu.search.service.CartoonWorksDetailsService;
import com.xu.search.to.CartoonWorksDetailsEntityTo;
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
@RequestMapping("search/cartoonworksdetails")
public class CartoonWorksDetailsController {
    @Autowired
    private CartoonWorksDetailsService cartoonWorksDetailsService;


    @ApiOperation(value = "作品章节目录查询", notes = "作品章节目录查询")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "worksType", value = "作品类型", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true),
                    @ApiImplicitParam(name = "worksId", value = "作品ID", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true)
            }
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "請求成功", response = R.class)
    })
    @GetMapping("/list")
    public R getWorksChapterList(@RequestParam Integer worksId) {
        List<CartoonWorksDetailsEntity> worksDetailsEntities=cartoonWorksDetailsService.getWorksChapterList(worksId);
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
            @ApiResponse(code = 200, message = "請求成功", response = R.class)
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

}
