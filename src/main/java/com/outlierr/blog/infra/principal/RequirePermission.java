package com.outlierr.blog.infra.principal;

import com.outlierr.blog.infra.exception.PermissionDeniedException;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.security.Permission;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface RequirePermission {
    /**
     * 是否需要权限才能访问
     * @return
     */
    boolean value() default true;

    Class<? extends RuntimeException> error() default PermissionDeniedException.class;

}
