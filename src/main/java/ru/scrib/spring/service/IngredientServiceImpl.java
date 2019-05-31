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
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    private IngredientDao ingredientDao;

    @Autowired
    private CategoryIngredientDao categoryIngredientDao;

    @Transactional
    public List<Ingredient> getIngredients() {
        return ingredientDao.getIngredients();
    }

    @Transactional
    public void saveIngredient(Ingredient ingredient) {
        long idCategory = Long.parseLong(ingredient.getCategoryIngredient().getName());
        CategoryIngredient category = categoryIngredientDao.getCategory(idCategory);
        ingredient.setCategoryIngredient(category);
        ingredientDao.saveIngredient(ingredient);
    }

    @Transactional
    public void deleteIngredient(long id) {
        ingredientDao.deleteIngredient(id);
    }

    @Transactional
    public Ingredient getIngredient(long id) {
        return ingredientDao.getIngredient(id);
    }
}
