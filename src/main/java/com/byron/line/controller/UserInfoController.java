package com.byron.line.controller;

import com.byron.line.common.annotation.VerifySign;
import com.byron.line.common.util.ResponseResult;
import com.byron.line.domain.User;
import com.byron.line.domain.ResgisteReq;
import com.byron.line.service.AppLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user", method = RequestMethod.POST)
@Api(value= "app账号Controller",tags = {"app账号接口"})
public class UserInfoController extends  BaseController{

    @Autowired
    private AppLoginService appLoginService;

    /**
     * app用户登录
     * @param user
     * @return
     */
//    @VerifySign(User.class)
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "app账户登录【sign】",notes = "返回登陆信息")
    public ResponseResult login(@RequestBody @ApiParam(name="请求对象",value="传入json格式",required=true) User user){
        ResponseResult rd = null;
        try {
            rd = appLoginService.login(user);
        } catch (Exception e) {
            exception(e,"/user/login");
        }
        return rd;
    }

    /**
     * 用户註冊
     * @param req
     * @return
     */
//    @VerifySign(ResgisteReq.class)
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "用户註冊【sign】",notes = "返回ok")
    public ResponseResult register(@RequestBody @ApiParam(name="请求对象",value="传入json格式",required=true) ResgisteReq req){
        ResponseResult rd = null;
        try {
            rd = appLoginService.register(req);
        } catch (Exception e) {
            exception(e,"/user/register");
        }
        return rd;
    }
}
