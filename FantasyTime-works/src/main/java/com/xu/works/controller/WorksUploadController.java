package com.xu.works.controller;


import com.xu.common.utils.R;
import com.xu.works.vo.WorksVo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.xu.works.service.WorksUploadService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 作品上传信息
 *
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-10 14:34:10
 */
@RestController
@RequestMapping("works/worksupload")
@Api(tags = "作品上传信息")
public class WorksUploadController {
    @Autowired
    private WorksUploadService worksUploadService;


    /**
     * @Description 查看自己上传的作品
     * @Author F3863479
     * @Date 2023/1/10 上午 10:33
     * @Params [WorksCategory, worksType]
     * @return com.xu.common.utils.R
     *
     */
    @ApiOperation(value = "查看自己上传的作品", notes = "查看自己上传的作品")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "worksType", value = "作品类型", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true),
                    @ApiImplicitParam(name = "page", value = "显示页数", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true),
                    @ApiImplicitParam(name = "limit", value = "每页条数", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true)
            }
    )
    @ApiResponses({
            @ApiResponse(code = 20000, message = "請求成功", response = R.class)
    })
    @GetMapping("/getUserUploadWorks")
    public R getUserUploadWorks(
                                @RequestParam Integer worksType,
                                @RequestParam Integer page,
                                @RequestParam Integer limit, HttpServletRequest request) {
        List<WorksVo> worksVos = worksUploadService.getUserUploadWorks( worksType,page,limit,  request);
        return R.ok().data("data", worksVos);
    }

}
