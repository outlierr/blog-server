package com.outlierr.blog.infra.exception;

public final class ResourceNotFoundException extends HttpStatusException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException() {
        this("找不到所请求的资源");
    }

    @Override
    public int statusCode() {
        return 404;
    }
}
