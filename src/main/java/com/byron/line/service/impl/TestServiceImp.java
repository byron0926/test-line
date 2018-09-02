package com.byron.line.service.impl;

import com.byron.line.req.TestReq;
import com.byron.line.res.TestRes;
import com.byron.line.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImp implements TestService {
    @Override
    public TestRes test(TestReq testReq) {
        return TestRes.builder().id(1l).name("lisi").status(1).build();
    }
}
