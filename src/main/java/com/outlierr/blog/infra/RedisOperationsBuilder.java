package com.outlierr.blog.infra;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.outlierr.blog.infra.codec.ExtendsCodecModule;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * GenericJackson2JsonRedisSerializer在序列化对象时会在json中加入@class全类名 方便反序列化时直接找到对象
 * Jackson2JsonRedisSerializer 反之，但在反序列化时需要给定对象 否则会报错
 */
@RequiredArgsConstructor
public class RedisOperationsBuilder {
    private final RedisConnectionFactory factory;


    private <T> Jackson2JsonRedisSerializer<T> jsonSerializer(Class<T> type) {
        Jackson2JsonRedisSerializer<T> serializer = new Jackson2JsonRedisSerializer<>(type);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new ExtendsCodecModule());
        serializer.setObjectMapper(objectMapper);
        return serializer;
    }

    /**
     * 创建一个新的 redisTemplate 默认 key 和 value 都使用 string 序列化(返回实例后可以配置其他序列方式)
     * @param <V>
     * @return
     */
    private <V> RedisTemplate<String, V> newTemplate() {
        RedisTemplate<String, V> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setDefaultSerializer(RedisSerializer.string());
        return template;
    }

    /**
     * 创建一个 HASH 类型的存储，使用字符串作为 HASH 的键，值使用 JSON 序列化。
     *
     * @param key 存储名
     * @param type HASH 值的类型
     * @param <V> HASH 值的类型
     */
    public <V> BoundHashOperations<String, String, V> bindHash(String key, Class<V> type) {
        RedisTemplate<String, V> template = this.newTemplate();
        template.setHashValueSerializer(jsonSerializer(type));
        template.afterPropertiesSet();
        return template.boundHashOps(key);
    }

    /**
     * 创建一个 LIST 类型的存储，其中的元素使用指定的序列化方式。
     *
     * @param key 存储名
     * @param serializer 序列化方式
     * @param <V> 元素的类型
     */
    public <V> BoundListOperations<String, V> bindList(String key, RedisSerializer<V> serializer) {
        RedisTemplate<String, V> template = this.newTemplate();
        template.setValueSerializer(serializer);
        template.afterPropertiesSet();
        return template.boundListOps(key);
    }

    /**
     * 创建一个 LIST 类型的存储，其中的元素使用 JSON 序列化。
     *
     * @param key 存储名
     * @param type 元素的类型
     * @param <V> 元素的类型
     */
    public <V> BoundListOperations<String, V> bindList(String key, Class<V> type) {
        return bindList(key, jsonSerializer(type));
    }
}
