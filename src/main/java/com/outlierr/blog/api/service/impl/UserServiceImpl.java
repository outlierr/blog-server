package com.outlierr.blog.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.outlierr.blog.api.entity.User;
import com.outlierr.blog.api.service.UserService;
import com.outlierr.blog.api.mapper.UserMapper;
import com.outlierr.blog.api.vo.UserVO;
import com.outlierr.blog.infra.principal.PrincipalFilter;
import com.outlierr.blog.infra.principal.WebPrincipal;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
* @author asl
* @description 针对表【user(用户个人信息表)】的数据库操作Service实现
* @createDate 2022-06-06 14:38:00
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Override
    public UserVO getUserById(int userId) {
        User user = userId > 0 ? this.lambdaQuery().eq(User::getId, userId).one() : WebPrincipal.GUEST.getUser();
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }
}




