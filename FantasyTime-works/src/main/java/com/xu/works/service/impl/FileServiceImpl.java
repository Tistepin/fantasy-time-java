package com.xu.works.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xu.common.constant.ResultCode;
import com.xu.common.utils.R;
import com.xu.works.controller.WorksChapterDetailedViewingContentController;
import com.xu.works.controller.WorksController;
import com.xu.works.controller.WorksDefaultImageController;
import com.xu.works.entity.OSSRemovesVo;
import com.xu.works.service.FileService;
import com.xu.works.utils.ZipUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
/**
 * @author 徐國紀
 * @Name F3863479
 * @create 2022-08-2022/8/22-下午 03:21
 */
@Service("FileService")
public class FileServiceImpl implements FileService {



    @Autowired
    WorksController worksController;
    @Autowired
    WorksDefaultImageController worksDefaultImageController;
    @Autowired
    WorksChapterDetailedViewingContentController worksChapterDetailedViewingContentControllerl;
    @Autowired
    StringRedisTemplate stringRedisTemplate;



    //上传文件存储在本地的根路径
    @Value("${file.gs.path}")
    private String path;

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
        String url = "";
        if (worksName != null) {
            url = path + worksName;
        }
        if (worksChapterId != null) {
            url += "/" + worksChapterId;
        }

        File file1 = new File(url);
        // 创建多个目录拥有上下级关系
        boolean mkdirs = file1.mkdirs();
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
        return file1 + "/" + Filename;

    }

    //上传文件存储在本地的根路径
    @Value("${file.gs.Zip}")
    private  String Zip;

    @Override
    public void removeFile(OSSRemovesVo datapath) {
        datapath.getUrls().forEach(item ->{
            File file = new File(item);
            deleteFiles(file,Zip);
        });

    }
    private static void deleteFiles(File directory,String zip) {
       if (directory.getParent().equals(zip)){
           directory.delete();
       }else{
           directory.delete();
           deleteFiles(directory.getParentFile(),zip);
       }
    }

    @Override
    public List<String> policy4(String worksName, MultipartFile zipFile) {
        //C:\Users\登录用户~1\AppData\Local\Temp\
        String pathName = Zip+ "\\"+ worksName+"\\压缩包";
        String dec =Zip + "\\"+ worksName;
        File file = new File(pathName);
        //如果文件夹不存在  创建文件夹
        if (!file.exists()) {
            file.mkdir();
        }
        //获取文件名（包括后缀）

        String pname = zipFile.getOriginalFilename();
        pathName =pathName+ "\\"+ pname;
        try {
            File dest = new File(pathName);
            zipFile.transferTo(dest);
            // 获取解压出来的文件名 不带后缀
            List<String> fileNames = ZipUtil.unZip(dest, dec, worksName);
            //解析完成   删除本次解析中生成的文件  删除此目录下的所有文件
            //ZipUtil.deleteFile(dec);

            return fileNames;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object GetLiunxFile() {
        File file = null;
        try {
            file = ResourceUtils.getFile(path);
        } catch (FileNotFoundException e) {
            try {
                file = ResourceUtils.getFile(path);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        }
        return file.list();
    }

    /**
     * @return void
     * @Description 获取这个作品的这一章的内容信息类别画画作品
     * @Author F3863479
     * @Date 2023/1/11 上午 10:40
     * @Params [worksId, worksChapterId, response]
     */
    @Override
//    @Async
    public void GetWorkContent(Integer worksId, Integer worksChapterId, Integer imageId, HttpServletResponse response, Integer imageDefaultStatus) {
        // 0 不是 1 是
        String Url = null;
        if (imageDefaultStatus == 1) {
            R imageData = worksDefaultImageController.getWorksDefaultImage(worksId.longValue());
            if (imageData.getCode() == ResultCode.SUCCESS.getCode()) {
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(imageData.getData().get("data"), SerializerFeature.WriteMapNullValue), Feature.OrderedField);
                Url = jsonObject.get("worksDefaultImage").toString();
            } else {
                throw new RuntimeException("没有该图片");
            }
        } else {
            R imageData = worksChapterDetailedViewingContentControllerl.getImageData(worksId, worksChapterId, imageId);
            if (imageData.getCode() == ResultCode.SUCCESS.getCode()) {
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(imageData.getData().get("data"), SerializerFeature.WriteMapNullValue), Feature.OrderedField);
                Url = jsonObject.get("worksChapterLocation").toString();
            } else {

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