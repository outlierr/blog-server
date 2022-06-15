package com.outlierr.blog.api.dto;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
public class LoginDTO {
    @NotNull
    public final String username;
    @NotNull
    public final String password;
    public final boolean remember;
}
