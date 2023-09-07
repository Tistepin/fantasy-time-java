package com.xu.works.controller;

import com.xu.common.utils.R;
import com.xu.works.entity.OSSRemovesVo;
import com.xu.works.service.FileService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @Description:
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-01-2023/1/10-下午 04:20
 */
@RestController
@RequestMapping("/oss")
public class OssController {
    @Autowired
    private FileService fileService;


    @PostMapping("/policy2")
    public R policy2(
            //@ApiParam(value = "文件", required = true)
            @RequestParam(value = "WorksName", required = false) String WorksName,
            @RequestParam(value = "worksChapterId", required = false) String worksChapterId,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) throws IOException {
        String url = fileService.upload(WorksName, worksChapterId, file);
        if (url != null && url.length() != 0) {

            return R.ok().data("messages", "上传成功").data("url", url);
        }
        return R.error();
    }

    /**
     * @return com.xu.common.utils.R
     * @Description 获取这个作品的这一章的内容信息
     * @Author F3863479
     * @Date 2023/1/11 上午 10:38
     * @Params []
     */
    @ApiOperation(value = "获取这个作品的这一章的指定图片", notes = "获取这个作品的这一章的指定图片")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "WorksId", value = "作品id", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true),
                    @ApiImplicitParam(name = "WorksChapterId", value = "作品章节Id", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = false),
                    // 0 不是 1 是
                    @ApiImplicitParam(name = "ImageDefaultStatus", value = "是否是作品封面 0 不是 1 是", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true),
                    @ApiImplicitParam(name = "ImageId", value = "图片ID", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = false)

            }
    )
    @ApiResponses({
            @ApiResponse(code = 20000, message = "請求成功", response = R.class)
    })
    @GetMapping("/getWorkContent")
    public void GetWorkContent(
            @RequestParam Integer WorksId,
            @RequestParam(required = false) Integer WorksChapterId,
            @RequestParam(required = false) Integer ImageId,
            @RequestParam Integer ImageDefaultStatus,
            HttpServletResponse response
    ) throws IOException {
        fileService.GetWorkContent(WorksId, WorksChapterId, ImageId, response, ImageDefaultStatus);

    }

    @ApiOperation("删除OSS文件")
    @ApiImplicitParam(name = "url", value = "图片存储路径", paramType = "query", dataType = "String",
            defaultValue = "", allowEmptyValue = true)
    @ApiResponses({
            @ApiResponse(code = 20000, message = "請求成功", response = R.class)
    })

    @DeleteMapping("/remove")
    public R remove(@RequestBody OSSRemovesVo ossRemovesV) {
        fileService.removeFile(ossRemovesV);
        return R.ok();
    }


    // 上传作品
    @PostMapping("/policy3")
    public R policy3(
            //@ApiParam(value = "文件", required = true)
            @RequestParam(value = "WorksName", required = false) String WorksName,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) throws IOException {
        String url = fileService.upload(WorksName, "封面", file);
        if (url != null && url.length() != 0) {

            return R.ok().data("messages", "上传成功").data("url", url);
        }
        return R.error();
    }

    //上传压缩包
    @PostMapping("/policy4")
    public R policy4(
            @RequestParam(value = "WorksName", required = false) String WorksName,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) throws IOException {
        List<String> urls = fileService.policy4(WorksName, file);
        if (urls != null && urls.size() != 0) {

            return R.ok().data("messages", "上传成功").data("url", urls);
        }
        return R.error();
    }

    //资源映射路径 前缀
    @Value("${file.gs.Zip}")
    public String localFilePrefix;

    //域名或本机访问地址
    @Value("${file.gs.domain}")
    public String domain;

    //上传文件存储在本地的根路径
    @Value("${file.gs.path}")
    private String path;
    @RequestMapping("/get")
    public  HashMap<String, String> get() {
        HashMap<String, String> map = new HashMap<>();
        map.put("localFilePrefix",localFilePrefix);
        map.put("domain",domain);
        map.put("path",path);
        return map;
    }
}
