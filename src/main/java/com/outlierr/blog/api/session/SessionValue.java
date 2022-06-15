package com.outlierr.blog.api.session;

import com.outlierr.blog.api.entity.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE) // 私有构造方法
public final class SessionValue<T> {
    public static final SessionValue<User> USER = new SessionValue<>("user");

    private final String name;

    @SuppressWarnings("unchecked")
    public T getForm(HttpSession session) {
        return (T) session.getAttribute(name);
    }

    public void removeFrom(HttpSession session) {
        session.removeAttribute(name);
    }

    public void setTo(HttpSession session, T value) {
        session.setAttribute(name, value);
    }

}
