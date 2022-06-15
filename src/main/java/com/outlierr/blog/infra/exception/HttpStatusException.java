package com.outlierr.blog.infra.exception;


public abstract class HttpStatusException extends RuntimeException{
    public abstract int statusCode();

    public HttpStatusException(String message) {
        super(message);
    }
}
