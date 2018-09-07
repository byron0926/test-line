package com.byron.line.mapper;

import com.byron.line.domain.Company;
import com.byron.line.domain.OrderDto;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyMapper {
    void updCompany(OrderDto orderDto);
    void insertCompany(Company company);
    Company queryCompanyRateById(Long id);
}
