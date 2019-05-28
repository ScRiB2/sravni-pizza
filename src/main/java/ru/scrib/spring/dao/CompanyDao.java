package ru.scrib.spring.dao;

import ru.scrib.spring.entity.pizza.Company;

import java.util.List;

public interface CompanyDao {
    public List<Company> getCompanies();
    public void saveCompany(Company company);
    void deleteCompany(long id);
    Company getCompany(long id);

    List<Company> getCompaniesByName(String[] companiesName);
}
