package com.outlierr.blog.infra.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.outlierr.blog.infra.codec.ExtendsCodecModule;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfiguration {
    /**
     * The purpose of this functional interface is to allow us to create configuration beans.
     * They will be applied to the default ObjectMapper created via Jackson2ObjectMapperBuilder:
     * Such customizer beans can be ordered (SpringBoot’s own customizer has an order of 0)
     * @return
     */
    // Jackson2ObjectMapperBuilder 会将自定义配置和其他已定义的配置混合在一起 如:springboot已经创建了一个
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonCodecCustomizer() {
        return builder -> builder
                .featuresToEnable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .modulesToInstall(ExtendsCodecModule.class);
    }
}
