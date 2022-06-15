package com.outlierr.blog.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.outlierr.blog.api.enums.AuthType;
import lombok.Data;

/**
 * 用户个人信息表
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户显示名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 用户头像
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     *
     */
    @TableField(value = "auth")
    private AuthType auth;

    /**
     * 
     */
    @TableField(value = "deleted")
    private Integer deleted;

    /**
     * 
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
     * 用户创建的 IP
     */
    @TableField(value = "create_ip")
    private byte[] createIp;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}