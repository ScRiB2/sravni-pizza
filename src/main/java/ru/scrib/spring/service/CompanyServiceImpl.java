package ru.scrib.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.scrib.spring.dao.CompanyDao;
import ru.scrib.spring.dao.PizzaDao;
import ru.scrib.spring.entity.pizza.Company;
import ru.scrib.spring.entity.pizza.Pizza;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private PizzaDao pizzaDao;

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
        Company company = getCompany(id);
        List<Pizza> pizzas = company.getPizzas();
        for (Pizza pizza : pizzas) {
            pizzaDao.deletePizza(pizza.getId());
        }

        companyDao.deleteCompany(id);
    }

    @Transactional
    public Company getCompany(long id) {
        return companyDao.getCompany(id);
    }
}
