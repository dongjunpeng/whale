package com.buterfleoge.whale.service.cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;

/**
 * redis 需要主从设置
 *
 * @author xiezhenzong
 *
 */
@Configuration
@EnableCaching
@PropertySource("classpath:/redis.properties")
public class CacheConfig {

    @Value("${redis.host}")
    private String host;

    @Value("${redis.port}")
    private int port;

    @Value("${redis.pass}")
    private String pass;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(name = "redisConnectionFactory")
    public RedisConnectionFactory getRedisConnectionFactory() {
        checkCacheServerConfig();
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(host);
        factory.setPort(port);
        factory.setPassword(pass);
        return factory;
    }

    @Bean(name = "cacheTemplate")
    public RedisTemplate<String, Object> getRedisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(getRedisConnectionFactory());
        return redisTemplate;
    }

    private void checkCacheServerConfig() {
        Assert.hasText(host, "host can't be empty");
        Assert.hasText(pass, "pass can't be empty");
        Assert.isTrue(port > 0, "port is invalid, port: " + port);
    }

}
