package com.outlierr.blog.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.outlierr.blog.api.entity.Article;
import com.outlierr.blog.api.service.ArticleService;
import com.outlierr.blog.api.mapper.ArticleMapper;
import org.springframework.stereotype.Service;

/**
* @author asl
* @description 针对表【article】的数据库操作Service实现
* @createDate 2022-06-06 14:38:00
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{

}




