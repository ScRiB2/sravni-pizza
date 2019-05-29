package ru.scrib.spring.dao;

import ru.scrib.spring.entity.pizza.CategoryIngredient;

import java.util.List;

public interface CategoryIngredientDao {
    List<CategoryIngredient> getCategories();
    void saveCategory(CategoryIngredient categoryIngredient);
    void deleteCategory(long id);
    CategoryIngredient getCategory(long id);
}
