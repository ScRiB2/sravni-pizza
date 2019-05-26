package ru.scrib.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.scrib.spring.dao.CompanyDao;
import ru.scrib.spring.entity.pizza.Company;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyDao companyDao;

    @Transactional
    public List<Company> getCompanies() {
        return companyDao.getCompanies();
    }

    @Transactional
    public void saveCompany(Company company) {
        companyDao.saveCompany(company);
    }

    @Transactional
    public void deleteCompany(long id) {
        companyDao.deleteCompany(id);
    }

    @Transactional
    public Company getCompany(long id) {
        return companyDao.getCompany(id);
    }
}
