package com.outlierr.blog.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.outlierr.blog.api.entity.Account;
import com.outlierr.blog.api.entity.User;
import com.outlierr.blog.api.mapper.UserMapper;
import com.outlierr.blog.api.service.AccountService;
import com.outlierr.blog.api.mapper.AccountMapper;
import com.outlierr.blog.api.service.SessionService;
import com.outlierr.blog.api.service.UserService;
import com.outlierr.blog.infra.exception.RequestArgumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
* @author asl
* @description 针对表【account(系统用户表)】的数据库操作Service实现
* @createDate 2022-06-06 14:38:00
*/
@Service
@RequiredArgsConstructor
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account>
    implements AccountService{

    private final UserService userService;

    @Override
    public User login(String username, String password, boolean remember) {
        Account account = this.lambdaQuery().eq(Account::getUsername, username).one();
        if(account == null || !DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8)).equals(account.getPassword())) {
            throw new RequestArgumentException("密码错误或用户不存在");
        }
        return userService.getById(account.getId());
    }
}




