package com.xu.thirdparty.service;


import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 徐國紀
 * @Name F3863479
 * @create 2022-08-2022/8/22-下午 03:20
 */
public interface FileService   {

    /**
     * @Description 文件上传至阿里云
     * @Author F3863479
     * @Date 2022/8/22 下午 03:20
     * @Params [inputStream, module, fileName]
     * @return java.lang.String
     *
     */

    void GetWorkContent(Integer worksId, Integer worksChapterId, Integer imageId, HttpServletResponse response, Integer imageDefaultStatus);

    String upload(String worksName, String worksChapterId, MultipartFile file) throws IOException;
}
