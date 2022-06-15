package com.outlierr.blog.api;

import com.outlierr.blog.api.dto.LoginDTO;
import com.outlierr.blog.api.entity.Keyword;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AccountControllerTest extends AbstractControllerTest{

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void login() throws Exception {
        LoginDTO loginDTO = new LoginDTO("admin", "admin", true);
        mockMvc.perform(post("/account/login").content(toJson(loginDTO))).andExpect(status().is(201));
    }

    @Test
    public void redisTest() {
        Keyword keyword = new Keyword();
        keyword.setValue("12");
        keyword.setId(1);
        redisTemplate.opsForValue().set("test", keyword);
        System.out.println();
    }
}
