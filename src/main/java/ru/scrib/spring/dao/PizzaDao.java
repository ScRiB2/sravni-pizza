package ru.scrib.spring.dao;

import ru.scrib.spring.entity.pizza.Pizza;
import ru.scrib.spring.filters.Filters;

import java.util.List;

public interface PizzaDao {
    public List<Pizza> getPizzas();

    public void savePizza(Pizza pizza);

    void deletePizza(long id);

    Pizza getPizza(long id);

    int getMinPrice();

    int getMaxPrice();

    List<Pizza> getPizzasWithFilters(Filters filters);
}
