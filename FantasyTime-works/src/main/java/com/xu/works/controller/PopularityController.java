package com.xu.works.controller;

import com.xu.common.utils.R;
import com.xu.works.service.PopularityService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * 作品人气
 *
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-05 17:23:59
 */
@RestController
@RequestMapping("works/popularity")
@Api(tags = "作品人气")
public class PopularityController {
    @Autowired
    private PopularityService popularityService;

    /**
     * @return com.xu.common.utils.R
     * @Description 今日添加作品人气 第二天清空
     * @Author F3863479
     * @Date 2023/1/7 上午 11:03
     * @Params [WorksId, UserId]
     */
    @ApiOperation(value = "今日添加作品人气", notes = "今日添加作品人气")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "WorksType", value = "页面类型", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true),
            @ApiImplicitParam(name = "WorksId", value = "作品ID", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true)
    })
    @ApiResponses({
            @ApiResponse(code = 20000, message = "請求成功", response = R.class)
    })
    @PostMapping("/saveWorksPopularity")
    public R saveWorksPopularity(@RequestParam Integer WorksType,
                                 @RequestParam Integer WorksId,
                                 HttpServletRequest request) {
        popularityService.saveWorksPopularity(WorksType,WorksId,request);
        return R.ok();
    }

}
