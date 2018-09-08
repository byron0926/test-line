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
@Api(value= "玩家充值入单请求controller",tags = {"玩家充值接口"})
public class OrderMathchController extends BaseController{

    @Autowired
    private OrderService orderService;

    @Autowired
    private CompanyService companyService;


    @RequestMapping("/deposit")
    public ResponseResult matchOrder(@RequestBody String param){

        ResponseResult rd = null;
        OrderDto orderDto = JSONObject.parseObject(param, OrderDto.class);
        log.info("商户请求参数对应实体类={}",orderDto);
        try {
            Company company = companyService.queryCompanyRateById(orderDto.getCompanyId());
            if(StringUtils.isEmpty(company)){
                rd = ResponseResult.builder().code(-9999).msg("商户不存在").build();
            }else {
                if(StringUtils.isEmpty(company.getRate())){
                    rd = ResponseResult.builder().code(-9999).msg("未配置商户费率").build();
                }
            }
            /*同步响应订单匹配信息给到商户（包含支付二维码/5位字符串凭据）*/
            rd = orderService.insertOrderAndReturnEntity(orderDto);
        } catch (Exception e) {
            e.printStackTrace();
            exception(e,"/order/deposit");
        }
        return rd;

    }



}
