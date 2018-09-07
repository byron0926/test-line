package com.byron.line;

import com.byron.line.service.AppLoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DoraTeslaApplicationTests {
    private static final Logger log = LoggerFactory
            .getLogger(DoraTeslaApplicationTests.class);

    @Autowired
    private AppLoginService appLoginService;

    @Test
    public void test(){

//        ResgisteReq resgisteReq = new ResgisteReq();
//        resgisteReq.setUserName("test1");
//        resgisteReq.setPassword("123456");
//        ResponseResult rd = appLoginService.register(resgisteReq);
//        System.out.println(rd);

    }


}
