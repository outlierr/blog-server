package com.outlierr.blog.infra;

import com.outlierr.blog.api.entity.Keyword;
import com.outlierr.blog.api.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.LocalDateTime;

@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisOperationsBuilder redisOperationsBuilder;
    @Test
    void test() {
        User user = new User();
        user.setId(1);
        user.setCreateTime(LocalDateTime.now());
        redisOperationsBuilder.bindList("list", User.class).rightPush(user);
//        Keyword list = redisOperationsBuilder.bindList("list", Keyword.class).leftPop();
    }
}
