package com.xu.works.config;

import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-01-2023/1/31-上午 10:39
 */
@Configuration
public class FeignConfig {

    /**
     * 超时时间配置
     */
    @Bean
    public Request.Options options(){
        return new Request.Options(5000,4000);
    }
}
