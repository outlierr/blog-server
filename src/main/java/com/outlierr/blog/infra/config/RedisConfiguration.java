package com.outlierr.blog.infra.config;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.outlierr.blog.infra.RedisOperationsBuilder;
import com.outlierr.blog.infra.codec.ExtendsCodecModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration(proxyBeanMethods = false)
public class RedisConfiguration {

    @Bean("springSessionDefaultRedisSerializer")
    public RedisSerializer<Object> redisJsonSerializer() {
        // 返回给前端的 time 为时间戳 存入 redis 的为格式化时间
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        // 序列化 json 中携带全名类表示
        objectMapper.activateDefaultTyping(
                LaissezFaireSubTypeValidator.instance ,
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.WRAPPER_ARRAY);
        objectMapper.registerModule(new ExtendsCodecModule());
        serializer.setObjectMapper(objectMapper);
        return serializer;
    }

    /**
     * 编写自定义的redisTemplate来修改默认的序列化方式 --> 默认为jdk序列器
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory, RedisSerializer<Object> redisSerializer) {
        //redis 基本配置
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        redisTemplate.setDefaultSerializer(redisSerializer);
        //设置 key 的序列化器
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setHashKeySerializer(RedisSerializer.string());

        redisTemplate.afterPropertiesSet();
        //允许使用事务
        redisTemplate.setEnableTransactionSupport(true);
        return redisTemplate;
    }

    /**
     * 适合跨语言的相同对象读取 JSON(例如 python 读取数据)
     * 提供 Jackson2JsonRedisSerializer 序列化的 json 不带全类名的方式 调用方法时需要传递对象类型
     * @param factory
     * @return
     */
    @Bean
    public RedisOperationsBuilder redisOperationsBuilder(RedisConnectionFactory factory) {
        return new RedisOperationsBuilder(factory);
    }


}
