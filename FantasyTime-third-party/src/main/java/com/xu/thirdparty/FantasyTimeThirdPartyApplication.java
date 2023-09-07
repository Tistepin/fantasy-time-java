package com.xu.thirdparty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description:
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-01-2023/1/10-下午 04:13
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan("com.xu")
@EnableFeignClients("com.xu.thirdparty")
@EnableDiscoveryClient // 服务注册与发现
public class FantasyTimeThirdPartyApplication {
    public static void main(String[] args) {
        SpringApplication.run(FantasyTimeThirdPartyApplication.class, args);
    }
}
