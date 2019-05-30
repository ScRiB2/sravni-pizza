package ru.scrib.spring.service;

import ru.scrib.spring.entity.pizza.Pizza;
import ru.scrib.spring.filters.Filters;

import java.util.List;

public interface PizzaService {
    public List<Pizza> getPizzas();

    public void savePizza(Pizza pizza);

    void deletePizza(long id);

    Pizza getPizza(long id);

    int getMinPrice();

    int getMaxPrice();

    List<Pizza> getPizzasWithFilters(Filters filters);
}
