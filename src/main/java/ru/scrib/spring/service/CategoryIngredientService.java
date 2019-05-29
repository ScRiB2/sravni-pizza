package ru.scrib.spring.service;

import ru.scrib.spring.entity.pizza.CategoryIngredient;

import java.util.List;

public interface CategoryIngredientService {
    List<CategoryIngredient> getCategories();
    void saveCategory(CategoryIngredient categoryIngredient);
    void deleteCategory(long id);
    CategoryIngredient getCategory(long id);
}
