package com.outlierr.blog.infra.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableConfigurationProperties(CorsProperties.class)
@RequiredArgsConstructor
public class GlobalCorsConfiguration {
    private final CorsProperties properties;

    private void configure(CorsConfiguration config) {
        if(properties.getTemplate() == CorsProperties.CorsTemplate.DEFAULT) {
            config.applyPermitDefaultValues(); // springboot 默认配置的跨域过滤器
        }else if(properties.getTemplate() == CorsProperties.CorsTemplate.ALLOW_ALL) {
            List<String> all = Collections.singletonList(CorsConfiguration.ALL);
            config.setAllowedHeaders(all);
            config.setAllowedMethods(all);
            config.setAllowedOriginPatterns(all);
            config.setAllowCredentials(true);
        }

        if (properties.getAllowedOrigins() != null) {
            config.setAllowedOrigins(properties.getAllowedOrigins());
        }
        if (properties.getAllowedOriginPatterns() != null) {
            config.setAllowedOriginPatterns(properties.getAllowedOriginPatterns());
        }

        // 以下这些配置通常在开发和生产环境之间也不会变化。
        if (properties.getAllowCredentials() != null) {
            config.setAllowCredentials(properties.getAllowCredentials());
        }
        if (properties.getMaxAge() != null) {
            config.setMaxAge(properties.getMaxAge());
        }
        if (properties.getAllowedMethods() != null) {
            config.setAllowedMethods(properties.getAllowedMethods());
        }
        if (properties.getAllowedHeaders() != null) {
            config.setAllowedHeaders(properties.getAllowedHeaders());
        }
        if (properties.getExposedHeaders() != null) {
            config.setExposedHeaders(properties.getExposedHeaders());
        }
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configure(configuration);
        source.registerCorsConfiguration("/**", configuration);

        FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        registrationBean.setFilter(new CorsFilter(source));
        return registrationBean;
    }
}
