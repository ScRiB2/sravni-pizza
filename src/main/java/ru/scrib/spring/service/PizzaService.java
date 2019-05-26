package ru.scrib.spring.service;

import ru.scrib.spring.entity.pizza.Pizza;

import java.util.List;

public interface PizzaService {
    public List<Pizza> getPizzas();
    public void savePizza(Pizza pizza);

    void deletePizza(long id);

    Pizza getPizza(long id);
}
