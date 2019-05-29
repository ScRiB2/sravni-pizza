package ru.scrib.spring.service;

import ru.scrib.spring.entity.pizza.Ingredient;

import java.util.List;

public interface IngredientService {
    List<Ingredient> getIngredients();
    void saveIngredient(Ingredient ingredient);
    void deleteIngredient(long id);
    Ingredient getIngredient(long id);
}
