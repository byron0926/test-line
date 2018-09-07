package com.byron.line.service;

import com.byron.line.domain.Company;

public interface CompanyService {

    void insertCompany(Company company);
    Company queryCompanyRateById(Long id);
}
