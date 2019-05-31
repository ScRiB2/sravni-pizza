package ru.scrib.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.scrib.spring.dao.CategoryIngredientDao;
import ru.scrib.spring.dao.IngredientDao;
import ru.scrib.spring.entity.pizza.CategoryIngredient;
import ru.scrib.spring.entity.pizza.Ingredient;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoryIngredientServiceImpl implements CategoryIngredientService {

    @Autowired
    private CategoryIngredientDao categoryIngredientDao;

    @Autowired
    private IngredientDao ingredientDao;

    @Transactional
    public List<CategoryIngredient> getCategories() {
        return categoryIngredientDao.getCategories();
    }

    @Transactional
    public void saveCategory(CategoryIngredient categoryIngredient) {
        categoryIngredientDao.saveCategory(categoryIngredient);
    }

    @Transactional
    public void deleteCategory(long id) {
        CategoryIngredient category = getCategory(id);
        List<Ingredient> ingredients = category.getIngredients();
        for (Ingredient ingredient : ingredients) {
            ingredientDao.deleteIngredient(ingredient.getId());
        }
        categoryIngredientDao.deleteCategory(id);
    }

    @Transactional
    public CategoryIngredient getCategory(long id) {
        return categoryIngredientDao.getCategory(id);
    }
}
