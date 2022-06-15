package com.outlierr.blog.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 * @TableName discusstion
 */
@TableName(value ="discusstion")
@Data
public class Discusstion implements Serializable {
    /**
     * 
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 1-文章 2-其他
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 评论的实体 ID
     */
    @TableField(value = "object_id")
    private Integer objectId;

    /**
     * 父评论 ID
     */
    @TableField(value = "parent")
    private Integer parent;

    /**
     * 实体楼层数
     */
    @TableField(value = "floor")
    private Integer floor;

    /**
     * 折叠楼层 ID
     */
    @TableField(value = "nest_id")
    private Integer nestId;

    /**
     * 折叠楼层数
     */
    @TableField(value = "nest_floor")
    private Integer nestFloor;

    /**
     * 折叠楼层大小
     */
    @TableField(value = "nest_size")
    private Integer nestSize;

    /**
     * 评论的用户
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 可以为空，则为匿名用户
     */
    @TableField(value = "nickname")
    private String nickname;

    /**
     * 
     */
    @TableField(value = "content")
    private String content;

    /**
     * 0-正常 1-待审核
     */
    @TableField(value = "state")
    private Integer state;

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
     * 评论的ip地址
     */
    @TableField(value = "address")
    private byte[] address;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}