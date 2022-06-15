package com.outlierr.blog.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.outlierr.blog.api.entity.Draft;
import com.outlierr.blog.api.service.DraftService;
import com.outlierr.blog.api.mapper.DraftMapper;
import org.springframework.stereotype.Service;

/**
* @author asl
* @description 针对表【draft】的数据库操作Service实现
* @createDate 2022-06-06 14:38:00
*/
@Service
public class DraftServiceImpl extends ServiceImpl<DraftMapper, Draft>
    implements DraftService{

}




