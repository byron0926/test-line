package com.byron.line.service;

import com.byron.line.domain.Company;
import org.springframework.stereotype.Repository;

public interface CompanyService {

    void insertCompany(Company company);
    Company queryCompanyRateById(Long id);
}
