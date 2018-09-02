package com.byron.line.controller;

import com.byron.line.common.util.ResponseResult;
import com.byron.line.req.TestReq;
import com.byron.line.service.TestService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api
@RequestMapping(value = "/byron/",method = RequestMethod.POST)
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/line")
    public ResponseResult getData(@RequestBody TestReq testReq){
        return ResponseResult.builder().obj(testService.test(testReq)).msg("获取数据成功").status(200).build();
    }


}
