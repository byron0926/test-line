package com.byron.line.service;

import com.byron.line.common.util.ResponseResult;
import com.byron.line.domain.Company;

public interface CompanyService {

    ResponseResult insertCompany(Company company);
    Company queryCompanyRateById(Long id);
}
