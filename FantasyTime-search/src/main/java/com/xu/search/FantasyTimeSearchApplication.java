package com.xu.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @Description:
 * @author: 徐國紀
 * @author: F3863479描配置文件 这里主要是权限模块的配置文件注入
 * @createTime: 2023-01-2023/1/30-上午 09:33
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient // 服务注册与发现
@ComponentScan("com.xu") // 扫

//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class FantasyTimeSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(FantasyTimeSearchApplication.class, args);
    }
}