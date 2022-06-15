package com.outlierr.blog.infra.exception;

public final class ResourceStateException extends HttpStatusException{
    public ResourceStateException(String message) {
        super(message);
    }

    public ResourceStateException() {
        this("资源的状态不允许执行请求的操作");
    }

    @Override
    public int statusCode() {
        return 409;
    }
}
