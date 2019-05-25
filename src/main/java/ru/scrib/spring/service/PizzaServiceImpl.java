package ru.scrib.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.scrib.spring.dao.PizzaDao;
import ru.scrib.spring.entity.pizza.Pizza;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PizzaServiceImpl implements PizzaService {

    @Autowired
    private PizzaDao pizzaDao;

    @Transactional
    public List<Pizza> getPizzas() {
        return pizzaDao.getPizzas();
    }

    @Transactional
    public void savePizza(Pizza pizza) {
        pizzaDao.savePizza(pizza);
    }
}
