package com.byron.line.service.impl;

import com.byron.line.common.util.ResponseResult;
import com.byron.line.domain.User;
import com.byron.line.domain.ResgisteReq;
import com.byron.line.mapper.UserMapper;
import com.byron.line.service.AppLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements AppLoginService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseResult login(User user) {
        userMapper.findUserByNameAndPassword(user);
        return ResponseResult.builder().code(200).msg("登陆成功").build();
    }

    @Override
    public ResponseResult register(ResgisteReq resgisteReq) {
        userMapper.insertUser(resgisteReq);
        return ResponseResult.builder().code(200).msg("注册成功").build();
    }
}
