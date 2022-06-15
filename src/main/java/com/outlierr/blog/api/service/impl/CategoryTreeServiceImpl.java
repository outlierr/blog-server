package com.outlierr.blog.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.outlierr.blog.api.entity.CategoryTree;
import com.outlierr.blog.api.service.CategoryTreeService;
import com.outlierr.blog.api.mapper.CategoryTreeMapper;
import org.springframework.stereotype.Service;

/**
* @author asl
* @description 针对表【category_tree】的数据库操作Service实现
* @createDate 2022-06-06 14:38:00
*/
@Service
public class CategoryTreeServiceImpl extends ServiceImpl<CategoryTreeMapper, CategoryTree>
    implements CategoryTreeService{

}




