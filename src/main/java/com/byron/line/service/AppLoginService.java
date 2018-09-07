package com.byron.line.service;

import com.byron.line.common.util.ResponseResult;
import com.byron.line.domain.User;
import com.byron.line.domain.ResgisteReq;

public interface AppLoginService {

    /**
     * app登陆
     * @param user
     * @return
     */
    ResponseResult login(User user);
    /**
     * app註冊
     * @param resgisteReq
     * @return
     */
    ResponseResult register(ResgisteReq resgisteReq);
}
