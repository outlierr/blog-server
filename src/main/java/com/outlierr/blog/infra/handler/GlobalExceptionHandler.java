package com.outlierr.blog.infra.handler;

import com.outlierr.blog.infra.exception.HttpStatusException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = HttpStatusException.class)
    public ResponseEntity<?> handle(HttpStatusException e) {
        log.debug(e.getMessage(), e);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("message", e.getMessage());
        return ResponseEntity.status(e.statusCode()).body(map);
    }

    // 控制器方法的参数绑定或校验失败
    @ExceptionHandler({BindException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<?> handle(Exception e) {
        log.debug("请求参数或内容不合法", e);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("message", e.getMessage());
        return ResponseEntity
                .status(400)
                .body(map);
    }
}
