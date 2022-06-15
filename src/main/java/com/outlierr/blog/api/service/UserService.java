package com.outlierr.blog.api.service;

import com.outlierr.blog.api.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.outlierr.blog.api.vo.UserVO;

/**
* @author asl
* @description 针对表【user(用户个人信息表)】的数据库操作Service
* @createDate 2022-06-06 14:38:00
*/
public interface UserService extends IService<User> {
    UserVO getUserById(int userId);
}
