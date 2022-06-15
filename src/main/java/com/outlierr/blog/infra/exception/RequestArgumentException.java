package com.outlierr.blog.infra.exception;

public final class RequestArgumentException extends HttpStatusException{
    public RequestArgumentException(String message) {
        super(message);
    }

    public RequestArgumentException() {
        this("请求参数或内容不合法");
    }

    @Override
    public int statusCode() {
        return 400;
    }
}
