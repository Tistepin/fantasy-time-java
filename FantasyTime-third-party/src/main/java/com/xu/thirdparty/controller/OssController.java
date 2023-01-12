package com.xu.thirdparty.controller;

import com.xu.common.utils.R;
import com.xu.thirdparty.service.FileService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Description:
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-01-2023/1/10-下午 04:20
 */
@RestController
public class OssController {
    @Autowired
    private FileService fileService;
    @PostMapping("/oss/policy2")
    public R policy2(
            //@ApiParam(value = "文件", required = true)
            @RequestParam(value = "WorksName",required = false) String WorksName,
            @RequestParam(value = "worksChapterId",required = false) String worksChapterId,
            @RequestParam(value = "file",required = false) MultipartFile file
    ) throws IOException {
        String url =fileService.upload(WorksName,worksChapterId,file);
        if (url !=null && url.length()!=0) {

            return R.ok().data("messages","上传成功").data("url", url);
        }
        return R.error();
    }
    /**
     * @Description 获取这个作品的这一章的内容信息
     * @Author F3863479
     * @Date 2023/1/11 上午 10:38
     * @Params []
     * @return com.xu.common.utils.R
     *
     */
    @ApiOperation(value = "获取这个作品的这一章的内容信息类别画画作品", notes = "获取这个作品的这一章的内容信息类别画画作品")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "WorksId", value = "作品id", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true),
                    @ApiImplicitParam(name = "WorksChapterId", value = "作品章节Id", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true),
                    @ApiImplicitParam(name = "ImageId", value = "图片ID", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true)

            }
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "請求成功", response = R.class)
    })
    @GetMapping("/getWorkContent")
    public R GetWorkContent(
            @RequestParam Integer WorksId,
            @RequestParam Integer WorksChapterId,
            @RequestParam Integer ImageId,
             HttpServletResponse response
    ) throws IOException {
        fileService.GetWorkContent(WorksId,WorksChapterId,ImageId,response);

        return R.ok();
    }
}