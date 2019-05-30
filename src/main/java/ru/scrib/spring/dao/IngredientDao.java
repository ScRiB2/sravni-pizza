package ru.scrib.spring.dao;

import ru.scrib.spring.entity.pizza.Ingredient;

import java.util.List;

public interface IngredientDao {
    List<Ingredient> getIngredients();

    void saveIngredient(Ingredient ingredient);

    void deleteIngredient(long id);

    Ingredient getIngredient(long id);
}
