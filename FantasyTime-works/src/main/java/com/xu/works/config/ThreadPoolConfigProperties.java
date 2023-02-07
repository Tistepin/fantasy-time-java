package com.xu.works.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-01-2023/1/6-下午 04:27
 */
@ConfigurationProperties(prefix = "fantasytime.thread")
@Component
@Data
public class ThreadPoolConfigProperties {
    // 核心大小
    private Integer coreSize;
    // 最大核心大小
    private Integer maxSize;
    // 过期时间秒
    private Integer keepAliveTime;
}
