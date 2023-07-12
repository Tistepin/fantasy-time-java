package com.xu.common.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 权限统一数据返回
 */
public class ResponseUtil {

    /**
     * @Description 返回請求頭token
     * @Author F3863479
     * @Date 2023/3/28 上午 07:57
     * @Params [response, r]
     * @return void
     *
     */
    public static void outHerder(HttpServletResponse response, R r) {
        ObjectMapper mapper = new ObjectMapper();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try {
            response.setHeader("FantasyTimetoken",r.getData().get("FantasyTimetoken").toString());
            mapper.writeValue(response.getWriter(), r);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * @Description 返回请求参数
     * @Author F3863479
     * @Date 2023/3/28 上午 07:57
     * @Params [response, r]
     * @return void
     *
     */
    public static void out(HttpServletResponse response, R r) {
        ObjectMapper mapper = new ObjectMapper();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try {
            mapper.writeValue(response.getWriter(), r);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
