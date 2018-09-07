package com.byron.line.service.impl;

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
    public void insertCompany(Company company) {
        companyMapper.insertCompany(company);
    }

    @Override
    public Company queryCompanyRateById(Long id) {
        return companyMapper.queryCompanyRateById(id);
    }
}
