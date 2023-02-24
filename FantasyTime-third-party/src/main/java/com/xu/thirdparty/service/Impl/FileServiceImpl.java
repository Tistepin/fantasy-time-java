package com.xu.thirdparty.service.Impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xu.common.constant.ResultCode;
import com.xu.common.utils.R;
import com.xu.thirdparty.fegin.WorksService;
import com.xu.thirdparty.service.FileService;
import lombok.var;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;


/**
 * @author 徐國紀
 * @Name F3863479
 * @create 2022-08-2022/8/22-下午 03:21
 */
@Service("FileService")
public class FileServiceImpl implements FileService {

    @Autowired
    WorksService WorksService;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 图片上传
     */
    @Override
    public String upload(String worksName, String worksChapterId, MultipartFile file) throws IOException {
        String Filename = file.getOriginalFilename();
        // 获取输入流
        InputStream inputStream = file.getInputStream();
        // 创建传输位置
        // 创建目录
        File file1 = new File("C:/Users/F3863479/Desktop/Test/" + worksName + "/" +worksChapterId);
        // 创建多个目录拥有上下级关系
        boolean mkdirs = file1.mkdirs();
        if (mkdirs){
            // 创建输出流
            FileOutputStream fileOutputStream = new FileOutputStream(file1 + "/" + Filename);
            //3.读数据
            byte[] buffer = new byte[5];
            //记录每次读取的字节个数
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, len);
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return file1 + "\\" + Filename;
        }else{
            return null;
        }

    }

    @Override
    public void removeFile(String url) {
        File file1 = new File(url);
        boolean delete = file1.delete();
    }

    /**
     * @return void
     * @Description 获取这个作品的这一章的内容信息类别画画作品
     * @Author F3863479
     * @Date 2023/1/11 上午 10:40
     * @Params [worksId, worksChapterId, response]
     */
    @Override
    @Async
    public void GetWorkContent(Integer worksId, Integer worksChapterId, Integer imageId, HttpServletResponse response, Integer imageDefaultStatus) {
        // 0 不是 1 是
        String Url=null;
        if (imageDefaultStatus==1){
            R imageData = WorksService.getWorksDefaultImage(worksId.longValue());
            if (imageData.getCode() == ResultCode.SUCCESS.getCode()) {
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(imageData.getData().get("data"), SerializerFeature.WriteMapNullValue), Feature.OrderedField);
                Url = jsonObject.get("worksDefaultImage").toString();
            }else{
                throw new RuntimeException("没有该图片");
            }
        }else{
            R imageData = WorksService.getImageData(worksId, worksChapterId, imageId);
            if (imageData.getCode() == ResultCode.SUCCESS.getCode()) {
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(imageData.getData().get("data"), SerializerFeature.WriteMapNullValue), Feature.OrderedField);
                Url = jsonObject.get("worksChapterLocation").toString();
            }else{

                throw new RuntimeException("没有该图片");
            }
        }
        if (Url != null) {
            response.setContentType("image/jpg"); // 不同文件的MimeType参考后续链接
            try {
                FileCopyUtils.copy(new FileInputStream(Url), response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new RuntimeException("没有该图片");
        }
    }


}