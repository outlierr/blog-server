package com.outlierr.blog.infra.config;

import com.outlierr.blog.infra.principal.AuthorizeAspect;
import com.outlierr.blog.infra.principal.PrincipalFilter;
import com.outlierr.blog.infra.principal.SecurityContextFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Any Servlet, Filter, or servlet *Listener instance that is a Spring bean is registered with the embedded container.
 */
@Configuration(proxyBeanMethods = false)
public class PrincipalConfiguration {
    @Bean
    public PrincipalFilter servletPrincipalFilter() {
        return new PrincipalFilter();
    }

    @Bean
    public AuthorizeAspect authorizeAspect() {
        return new AuthorizeAspect();
    }

    @Bean
    public SecurityContextFilter securityContextFilter() {
        return new SecurityContextFilter();
    }
}
