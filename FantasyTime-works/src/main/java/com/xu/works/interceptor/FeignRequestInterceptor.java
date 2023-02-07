package com.xu.works.interceptor;

import feign.RequestInterceptor;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-01-2023/1/31-上午 11:30
 */
@Configuration
@Data
public class FeignRequestInterceptor{
    /**
     * feign调用丢失token解决方式，新增拦截器
     * @return
     */
    @Bean
    public RequestInterceptor requestInterceptor(){

        return template -> {

            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

            if(attributes!=null){

                HttpServletRequest httpServletRequest = attributes.getRequest();

                if(httpServletRequest == null){
                    return;
                }
                String token = httpServletRequest.getHeader("FantasyTimetoken");
                template.header("FantasyTimetoken",token);
            }
        };

    }
}
