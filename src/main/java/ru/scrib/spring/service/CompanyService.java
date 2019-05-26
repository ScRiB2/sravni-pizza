package ru.scrib.spring.service;

import ru.scrib.spring.entity.pizza.Company;

import java.util.List;

public interface CompanyService {
    public List<Company> getCompanies();
    public void saveCompany(Company company);
    void deleteCompany(long id);
    Company getCompany(long id);
}
