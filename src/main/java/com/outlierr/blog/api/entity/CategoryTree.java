package com.outlierr.blog.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName category_tree
 */
@TableName(value ="category_tree")
@Data
public class CategoryTree implements Serializable {
    /**
     * 祖先节点
     */
    private Integer ancestor;

    /**
     * 后裔节点
     */
    private Integer descendant;

    /**
     * 间隔辈分
     */
    @TableField(value = "distance")
    private Object distance;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}