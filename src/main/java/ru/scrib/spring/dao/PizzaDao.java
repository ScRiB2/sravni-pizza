package ru.scrib.spring.dao;

import ru.scrib.spring.entity.pizza.Pizza;

import java.util.List;

public interface PizzaDao {
    public List<Pizza> getPizzas();
    public void savePizza(Pizza pizza);

    void deletePizza(long id);

    Pizza getPizza(long id);
}
