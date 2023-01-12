package com.xu.search.config;


import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.HashMap;
import java.util.Map;
/*
 * 整合springCache简化缓存开发
 *   1.引入依赖spring-boot-starter-cache ， spring-boot-starter-data-redis
 *   2.写配置
 *       1.自动配置
 *           CacheAutoConfiguration 导入 redisCacheConfiguration
 *       配置使用redis作为缓存
 *   3.注解
 *    @Cacheable 触发将数据保存到缓存里边
 *    @CachePut  不影响方法执行保存缓存
 *    @CacheEvict 将数据从缓存中删除
 *    @Caching 多个操作集合
 *    @CacheConfig 在类级别共享缓存相同配置
 *   4.开启缓存功能 @EnableCaching
 *   5.只需要使用注解就可以使用缓存功能
* */
/**
 * @Description: 缓存配置
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-01-2023/1/6-下午 01:50
 */
@Configuration
@EnableConfigurationProperties(CacheProperties.class)
public class MyCacheConfig {

    @Bean
    RedisCacheConfiguration redisCacheConfiguration(CacheProperties cacheProperties){
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        config = config.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()));
        // 序列号插入
        config = config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
        // 获取cache配置 配置插入redis数据的属性
        CacheProperties.Redis redisProperties = cacheProperties.getRedis();

        // 时间
        if (redisProperties.getTimeToLive() != null) {
            config = config.entryTtl(redisProperties.getTimeToLive());
        }
        // 是否使用前缀
        if (!redisProperties.isUseKeyPrefix()) {
            config = config.disableKeyPrefix();
        }
        // 配置key的前缀
        if (redisProperties.getKeyPrefix() != null) {
            config = config.prefixCacheNameWith(redisProperties.getKeyPrefix());
//            config = config.prefixCacheNameWith(redisProperties.getKeyPrefix());
        }
        // 是否缓存控制 不缓存解决缓存击穿
        if (!redisProperties.isCacheNullValues()) {
            config = config.disableCachingNullValues();
        }
        return config;
    }

}
