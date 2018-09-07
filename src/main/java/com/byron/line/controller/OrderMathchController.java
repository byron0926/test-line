package com.byron.line.controller;

import com.alibaba.fastjson.JSONObject;
import com.byron.line.common.util.ResponseResult;
import com.byron.line.common.util.StringUtils;
import com.byron.line.domain.Company;
import com.byron.line.domain.OrderDto;
import com.byron.line.service.CompanyService;
import com.byron.line.service.OrderService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/order", method = RequestMethod.POST)
@Api(value= "玩家充值入单订单controller",tags = {"玩家充值接口"})
public class OrderMathchController extends BaseController{

    @Autowired
    private OrderService orderService;

    @Autowired
    private CompanyService companyService;


    @RequestMapping("/deposit")
    public ResponseResult matchOrder(@RequestBody String param){

        ResponseResult rd = null;
        OrderDto orderDto = JSONObject.parseObject(param, OrderDto.class);


        try {

            Company company = companyService.queryCompanyRateById(orderDto.getCompanyId());
            if(StringUtils.isNotEmpty(company)){
                if(StringUtils.isEmpty(company.getRate())){
                    rd = ResponseResult.builder().code(-9999).msg("未配置商户费率").build();
                    return rd;
                }
            }
            rd = orderService.insertOrderAndReturnEntity(orderDto);
        } catch (Exception e) {
            e.printStackTrace();
            exception(e,"/order/deposit");
        }
        return rd;

    }



}
