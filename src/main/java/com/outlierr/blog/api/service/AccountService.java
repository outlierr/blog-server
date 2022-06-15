package com.outlierr.blog.api.service;

import com.outlierr.blog.api.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;
import com.outlierr.blog.api.entity.User;

/**
* @author asl
* @description 针对表【account(系统用户表)】的数据库操作Service
* @createDate 2022-06-06 14:38:00
*/
public interface AccountService extends IService<Account> {
    User login(String username, String password, boolean remember);
}
