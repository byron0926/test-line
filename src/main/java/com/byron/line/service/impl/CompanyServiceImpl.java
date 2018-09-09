package com.byron.line.service.impl;

import com.byron.line.common.util.ResponseResult;
import com.byron.line.domain.Company;
import com.byron.line.mapper.CompanyMapper;
import com.byron.line.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public ResponseResult insertCompany(Company company) {
        int result = companyMapper.insertCompany(company);
        ResponseResult rd = null;
        if(1==result){
            rd = ResponseResult.builder().object(company).code(200).msg("商户配置成功").build();
        }else {
            rd = ResponseResult.builder().code(-9999).msg("商户配置失败").build();
        }
        return rd;
    }

    @Override
    public Company queryCompanyRateById(Long id) {
        return companyMapper.queryCompanyRateById(id);
    }
}
