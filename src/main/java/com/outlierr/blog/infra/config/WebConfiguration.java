package com.outlierr.blog.infra.config;

import com.outlierr.blog.api.service.impl.SessionServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.session.DefaultCookieSerializerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.FileNotFoundException;
import java.util.stream.Stream;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    //    保留默认的映射路径
    private final String[] CLASSPATH_RESOURCE_LOCATIONS = {"classpath:/META-INF/resources/",
            "classpath:/resources/", "classpath:/static/", "classpath:/public/"};

    @Value("${app.data-dir}")
    private String dataDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 多个 configuration 配置的 addResourceHandler的 url 存在多个相同路径时，只会使用最新注册的那个资源位置
        ResourceHandlerRegistration resourceHandlerRegistration = registry.addResourceHandler("/**");
        try {
            // toString 是 URI 形式字符串
            resourceHandlerRegistration.addResourceLocations(ResourceUtils.getURL(dataDir).toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Stream.of(CLASSPATH_RESOURCE_LOCATIONS).forEach(resourceHandlerRegistration::addResourceLocations);
    }
    @Bean
    DefaultCookieSerializerCustomizer customizer() {
        return cookieSerializer -> {
            cookieSerializer.setUseBase64Encoding(false);
            cookieSerializer.setRememberMeRequestAttribute(SessionServiceImpl.REMEMBER_ME_ATTR);
        };
    }
}
