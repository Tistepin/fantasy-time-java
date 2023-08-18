package com.xu.thirdparty.config;


import feign.Request;
import feign.Request.Options;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

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
        return new Request.Options(600000, TimeUnit.MILLISECONDS,
                600000,TimeUnit.MILLISECONDS,
                true);
    }
}
