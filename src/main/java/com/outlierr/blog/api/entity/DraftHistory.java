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
 * @TableName draft_history
 */
@TableName(value ="draft_history")
@Data
public class DraftHistory implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 保存次数
     */
    private Integer saveCount;

    /**
     * 
     */
    @TableField(value = "title")
    private String title;

    /**
     * 
     */
    @TableField(value = "content")
    private String content;

    /**
     * 
     */
    @TableField(value = "summary")
    private String summary;

    /**
     * 
     */
    @TableField(value = "keywords")
    private String keywords;

    /**
     * 
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}