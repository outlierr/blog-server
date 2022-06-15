package com.outlierr.blog.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.outlierr.blog.api.entity.DraftHistory;
import com.outlierr.blog.api.service.DraftHistoryService;
import com.outlierr.blog.api.mapper.DraftHistoryMapper;
import org.springframework.stereotype.Service;

/**
* @author asl
* @description 针对表【draft_history】的数据库操作Service实现
* @createDate 2022-06-06 14:38:00
*/
@Service
public class DraftHistoryServiceImpl extends ServiceImpl<DraftHistoryMapper, DraftHistory>
    implements DraftHistoryService{

}




