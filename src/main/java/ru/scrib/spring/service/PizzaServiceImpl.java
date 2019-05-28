package ru.scrib.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.scrib.spring.dao.CompanyDao;
import ru.scrib.spring.dao.PizzaDao;
import ru.scrib.spring.entity.pizza.Company;
import ru.scrib.spring.entity.pizza.Pizza;
import ru.scrib.spring.filters.Filters;
import ru.scrib.spring.string.StringHelper;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PizzaServiceImpl implements PizzaService {

    @Autowired
    private PizzaDao pizzaDao;

    @Autowired
    private CompanyDao companyDao;

    @Transactional
    public List<Pizza> getPizzas() {
        return pizzaDao.getPizzas();
    }

    @Transactional
    public void savePizza(Pizza pizza) {
        pizza.setName(StringHelper.convertFromUTF8(pizza.getName()));
        long idCompany = Long.parseLong(pizza.getCompany().getName());
        Company company = companyDao.getCompany(idCompany);
        pizza.setCompany(company);
        pizzaDao.savePizza(pizza);
    }

    @Transactional
    public void deletePizza(long id) {
        pizzaDao.deletePizza(id);
    }

    @Transactional
    public Pizza getPizza(long id) {
        return pizzaDao.getPizza(id);
    }

    @Transactional
    public int getMinPrice() {
        return pizzaDao.getMinPrice();
    }

    @Transactional
    public int getMaxPrice() {
        return pizzaDao.getMaxPrice();
    }

    @Transactional
    public List<Pizza> getPizzasWithFilters(Filters filters) {
        return pizzaDao.getPizzasWithFilters(filters);
    }
}
