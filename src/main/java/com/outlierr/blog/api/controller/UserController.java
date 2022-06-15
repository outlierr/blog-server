package com.outlierr.blog.api.controller;


import com.outlierr.blog.api.service.UserService;
import com.outlierr.blog.api.session.SessionValue;
import com.outlierr.blog.api.vo.UserVO;
import com.outlierr.blog.infra.principal.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public UserVO get() {
        return userService.getUserById(SecurityContext.getUser().getId());
    }

    @DeleteMapping
    public ResponseEntity<Void> logout(HttpSession session) {
        SessionValue.USER.removeFrom(session);
        return ResponseEntity.status(HttpStatus.RESET_CONTENT).build();
    }
}
