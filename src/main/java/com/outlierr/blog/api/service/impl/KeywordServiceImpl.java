package com.outlierr.blog.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.outlierr.blog.api.entity.Keyword;
import com.outlierr.blog.api.service.KeywordService;
import com.outlierr.blog.api.mapper.KeywordMapper;
import org.springframework.stereotype.Service;

/**
* @author asl
* @description 针对表【keyword】的数据库操作Service实现
* @createDate 2022-06-06 14:38:00
*/
@Service
public class KeywordServiceImpl extends ServiceImpl<KeywordMapper, Keyword>
    implements KeywordService{

}




