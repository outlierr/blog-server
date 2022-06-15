package com.outlierr.blog.infra.principal;

import com.outlierr.blog.api.entity.User;
import org.springframework.core.annotation.Order;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

/**
 * <h2>关于 CSRF 的问题</h2>
 * 现代的浏览器都支持 SameSite Cookie，无需再自己设计 CSRF token 机制。
 */
@Order(10_000)
public final class PrincipalFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        super.doFilter(new Wrapper(request), response, chain);
    }

    private static class Wrapper extends HttpServletRequestWrapper {
        private Wrapper(HttpServletRequest request){
            super(request);
        };

        @Override
        public Principal getUserPrincipal() {
            return Optional
                    .ofNullable(getSession(false))
                    .map(s -> s.getAttribute("user"))
                    .map(user -> new WebPrincipal((User) user))
                    .orElse(WebPrincipal.GUEST);
        }
    }
}
