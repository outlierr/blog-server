package com.outlierr.blog.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
public abstract class AbstractControllerTest {
    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    protected ObjectMapper objectMapper;

    @BeforeEach
    final void setup() {
        MockHttpServletRequestBuilder builder = get("/")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8");
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .defaultRequest(builder)
                .alwaysDo(r -> r.getResponse().setCharacterEncoding("UTF-8"))
                .build();

    }

    /**
     * 把对象序列化为 JSON 字符串，因为比较长所以提取单独一个方法。
     *
     * @param value 对象
     * @return JSON 字符串
     */
    protected final String toJson(Object value) throws IOException {
        return objectMapper.writeValueAsString(value);
    }
}
