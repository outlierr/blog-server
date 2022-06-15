package com.outlierr.blog.infra.principal;

import com.outlierr.blog.api.entity.User;

public final class SecurityContext {
    private static final ThreadLocal<WebPrincipal> threadLocal = new ThreadLocal<>();

    private SecurityContext(){};

    public static void setThreadLocal(WebPrincipal webPrincipal) {
        threadLocal.set(webPrincipal);
    }

    /** 需要添加 SecurityContextFilter 后才能使用 SecurityContext */
    public static WebPrincipal getPrincipal() {
        return threadLocal.get();
    }

    public static User getUser() {
        return getPrincipal().getUser();
    }
}
