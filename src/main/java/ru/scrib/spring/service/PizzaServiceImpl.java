package ru.scrib.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.scrib.spring.dao.CategoryIngredientDao;
import ru.scrib.spring.dao.CompanyDao;
import ru.scrib.spring.dao.IngredientDao;
import ru.scrib.spring.dao.PizzaDao;
import ru.scrib.spring.entity.pizza.*;
import ru.scrib.spring.filters.Filters;
import ru.scrib.spring.string.StringHelper;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PizzaServiceImpl implements PizzaService {

    @Autowired
    private PizzaDao pizzaDao;

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private IngredientDao ingredientDao;

    @Autowired
    private CategoryIngredientDao categoryIngredientDao;

    @Transactional
    public List<Pizza> getPizzas() {
        return pizzaDao.getPizzas();
    }

    @Transactional
    public void savePizza(Pizza pizza) {
        pizza.setName(StringHelper.convertFromUTF8(pizza.getName()));

        long idCompany = Long.parseLong(pizza.getCompany().getName());
        Company company = companyDao.getCompany(idCompany);
        pizza.setCompany(company);

        List<Ingredient> ingredients = new ArrayList<>();
        if (pizza.getIngredients() == null) {
            ingredients.add(ingredientDao.getIngredient(10));
            pizza.setIngredients(ingredients);
        } else {
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(pizza.getIngredients().get(0).getName());
            List<Long> ids = new ArrayList<>();
            while (m.find()) {
                ids.add(Long.parseLong(m.group()));
            }
            for (Long id : ids) {
                Ingredient ingredient = ingredientDao.getIngredient(id);
                ingredients.add(ingredient);
            }
        }
        pizza.setIngredients(ingredients);

        pizzaDao.savePizza(pizza);
    }

    @Transactional
    public void deletePizza(long id) {
        pizzaDao.deletePizza(id);
    }

    @Transactional
    public Pizza getPizza(long id) {
        return pizzaDao.getPizza(id);
    }

    @Transactional
    public int getMinPrice() {
        return pizzaDao.getMinPrice();
    }

    @Transactional
    public int getMaxPrice() {
        return pizzaDao.getMaxPrice();
    }

    @Transactional
    public List<Pizza> getPizzasWithFilters(Filters filters) {
        List<Company> companies = companyDao.getCompaniesByName(filters.getCompaniesName());
        filters.setCompanyList(companies);

        List<CategoryIngredient> categories = categoryIngredientDao.getCategoriesByName(filters.getCategoriesName());
        Set<String> categorySet = new HashSet<>();
        for (CategoryIngredient category : categories) {
            categorySet.add(category.getName());
        }
        filters.setCategories(categorySet);

        if (filters.getSizes() == null || filters.getSizes().length == 0)
            filters.setSizePizzas(SizePizza.values());
        else {
            String[] sizes = filters.getSizes();
            SizePizza[] newSizes = new SizePizza[sizes.length];
            for (int i = 0; i < sizes.length; i++) {
                newSizes[i] = SizePizza.getType(sizes[i]);
            }
            filters.setSizePizzas(newSizes);
        }

        List<Pizza> pizzas = pizzaDao.getPizzasWithFilters(filters);
        for (Pizza pizza : pizzas) {
            pizza.getIngredients().size();
        }
        return pizzas;
    }
}
