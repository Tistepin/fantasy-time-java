package com.xu.works;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Description:
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-01-2023/1/2-下午 02:48
 */
@EnableRabbit
@SpringBootApplication
@EnableDiscoveryClient // 服务注册与发现
@ComponentScan("com.xu") // 扫描配置文件 这里主要是权限模块的配置文件注入
@MapperScan("com.xu.works.dao")
@EnableCaching // 开启spring-cache缓存
@EnableFeignClients("com.xu.works.feign")
@EnableScheduling//开启定时任务

//@EnableAutoDataSourceProxy//seata启用自动数据源代理
public class FantasyTimeWorksApplication {
    public static void main(String[] args) {
        SpringApplication.run(FantasyTimeWorksApplication.class, args);
    }
}
