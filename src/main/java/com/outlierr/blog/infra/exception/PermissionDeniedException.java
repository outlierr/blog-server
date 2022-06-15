package com.outlierr.blog.infra.exception;

public final class PermissionDeniedException extends HttpStatusException{
    public PermissionDeniedException(String message) {
        super(message);
    }

    public PermissionDeniedException() {
        this("你没有权限执行这个操作");
    }

    @Override
    public int statusCode() {
        return 401;
    }
}
