package com.byron.line.mapper;

import com.byron.line.common.util.ResponseResult;
import com.byron.line.domain.User;
import com.byron.line.domain.ResgisteReq;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginMapper {
    /*登陆*/
    ResponseResult login(User user);
    /*注册*/
    ResponseResult register(ResgisteReq resgisteReq);
}
