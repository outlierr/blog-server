package com.outlierr.blog.api.service.impl;

import com.outlierr.blog.api.entity.User;
import com.outlierr.blog.api.service.SessionService;
import com.outlierr.blog.api.session.SessionTable;
import com.outlierr.blog.api.session.SessionValue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Service
public class SessionServiceImpl implements SessionService {
    public static final String REMEMBER_ME_ATTR = "remember-me";

    private final SessionTable sessionTable;

    @Override
    public void putUser(HttpServletRequest request, User user, boolean remember) {
        HttpSession session = request.getSession(true);
        /**
         * 考虑这种情况：
         * 登录（记住） -> 30 天之后 Cookie 过期 -> 继续登录（不记住）。
         * 第二次登录时已有一个长期的 Cookie，由于 Spring Session (签发的 cookie 的 maxAge 为 Integer的最大值) 无法修改现有的 age，
         * 导致即使未选择记住但仍长期保持了登录。
         *
         * 所以重新登陆时需要刷新下 ID，以便更改 Cookie 的过期时间
         */
        if(!session.isNew()) {
            request.changeSessionId();
        }
//        SessionValue.USER.setTo(session, user);
        session.setAttribute("user", user);
        sessionTable.addUser(user.getId(), session.getId());

        // 使用 Spring Session 默认的机制来记住登录状态。
        // org.springframework.session.web.http.DefaultCookieSerializer#getMaxAge
        if(remember) {
            request.setAttribute(REMEMBER_ME_ATTR, true);
        }
    }
}
