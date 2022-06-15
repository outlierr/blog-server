package com.outlierr.blog.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.outlierr.blog.api.entity.Category;
import com.outlierr.blog.api.service.CategoryService;
import com.outlierr.blog.api.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

/**
* @author asl
* @description 针对表【category(分类表)】的数据库操作Service实现
* @createDate 2022-06-06 14:38:00
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

}




