package com.byron.line.controller;


import com.byron.line.common.util.ResponseResult;
import com.byron.line.domain.Company;
import com.byron.line.service.CompanyService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api
@RequestMapping(value = "/company",method = RequestMethod.POST)
public class CompanyConfigController extends BaseController{

    @Autowired
    private CompanyService companyService;

    @RequestMapping("/init")
    public ResponseResult initCompany(@RequestBody Company company){
        ResponseResult rd = null;
        try{
            rd = companyService.insertCompany(company);
        }catch (Exception e){
            e.printStackTrace();
            exception(e,"company/config");
        }
        return rd;
    }

}
