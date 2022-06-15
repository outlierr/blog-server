package com.outlierr.blog.infra.config;

import com.outlierr.blog.infra.handler.TimeMetaObjectHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class MybatisPlusConfiguration {
    @Bean
    public TimeMetaObjectHandler timeMetaObjectHandler() {
        return new TimeMetaObjectHandler();
    }
}
