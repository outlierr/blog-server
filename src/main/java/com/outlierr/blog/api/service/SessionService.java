package com.outlierr.blog.api.service;

import com.outlierr.blog.api.entity.User;

import javax.servlet.http.HttpServletRequest;

public interface SessionService {

    void putUser(HttpServletRequest request, User user, boolean remember);
}
