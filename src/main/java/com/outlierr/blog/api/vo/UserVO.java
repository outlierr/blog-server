package com.outlierr.blog.api.vo;

import com.outlierr.blog.api.enums.AuthType;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
public class UserVO {
    public int id;
    public String name;
    public String avatar;
    public AuthType auth;
    public LocalDateTime createTime;
}
