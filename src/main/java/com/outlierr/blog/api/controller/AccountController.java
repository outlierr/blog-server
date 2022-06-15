package com.outlierr.blog.api.controller;

import com.outlierr.blog.api.dto.LoginDTO;
import com.outlierr.blog.api.entity.Keyword;
import com.outlierr.blog.api.entity.Test;
import com.outlierr.blog.api.entity.User;
import com.outlierr.blog.api.service.AccountService;
import com.outlierr.blog.api.service.SessionService;
import com.outlierr.blog.api.session.SessionTable;
import com.outlierr.blog.api.session.SessionValue;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;
    private final SessionService sessionService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Validated LoginDTO data, HttpServletRequest request) {
        User user = accountService.login(data.username, data.password, data.remember);
        sessionService.putUser(request, user, data.remember);
        return ResponseEntity.created(URI.create("/user")).build();
    }

}
