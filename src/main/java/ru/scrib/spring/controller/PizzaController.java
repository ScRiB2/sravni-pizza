package ru.scrib.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.scrib.spring.entity.pizza.Company;
import ru.scrib.spring.entity.pizza.Ingredient;
import ru.scrib.spring.entity.pizza.Pizza;
import ru.scrib.spring.entity.pizza.SizePizza;
import ru.scrib.spring.service.CompanyService;
import ru.scrib.spring.service.IngredientService;
import ru.scrib.spring.service.PizzaService;
import ru.scrib.spring.string.StringHelper;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/pizza")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private IngredientService ingredientService;

    private Map<SizePizza, String> sizes;
    private List<Company> companies;

    @PostConstruct
    protected void loadSizes() {
        sizes = new LinkedHashMap<>();
        sizes.put(SizePizza.STANDART, "Обычная");
        sizes.put(SizePizza.MEDIUM, "Средняя");
        sizes.put(SizePizza.BIG, "Большая");

    }

    @GetMapping("/list")
    public String listCustomers(Model theModel) {
        theModel.addAttribute("pizzas", pizzaService.getPizzas());
        return "pizza/pizza-list";
    }

    @GetMapping("/addPizza")
    public String addPizza(Model model, HttpServletRequest request) {
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("sizes", sizes);
        model.addAttribute("companies", companyService.getCompanies());
        model.addAttribute("ingredients", ingredientService.getIngredients());
        return "pizza/pizza-form";
    }

    @GetMapping("/delete")
    public String deletePizza(@RequestParam("pizzaId") long id) {
        pizzaService.deletePizza(id);
        return "redirect:/pizza/list";
    }

    @GetMapping("/update")
    public String updatePizza(@RequestParam("pizzaId") long id,
                              Model model) {
        model.addAttribute("pizza", pizzaService.getPizza(id));
        model.addAttribute("sizes", sizes);
        model.addAttribute("companies", companyService.getCompanies());
        model.addAttribute("ingredients", ingredientService.getIngredients());
        return "pizza/pizza-form";
    }

    @GetMapping("/info")
    public String infoPizza(@RequestParam("pizzaId") long id,
                            Model model) {
        Pizza pizza = pizzaService.getPizza(id);
        model.addAttribute("pizza", pizza);
        return "pizza/pizza-info";
    }

    @PostMapping("/addPizza")
    public String addPostPizza(@ModelAttribute("pizza") Pizza pizza, Model model) {
        pizza.setName(StringHelper.convertFromUTF8(pizza.getName()));
        String str = pizza.getCompany().getName();
        int index = str.indexOf("'");
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(pizza.getCompany().getName());
        List<Long> ids = new ArrayList<>();
        while (m.find()) {
            ids.add(Long.parseLong(m.group()));
        }
        Company company = companyService.getCompany(ids.get(1));
        pizza.setCompany(company);


        List<Ingredient> ingredients = pizza.getIngredients();
        List<Ingredient> newIngredients = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            newIngredients.add(ingredientService.getIngredient(Long.parseLong(ingredient.getName())));
        }
        pizza.setIngredients(newIngredients);
        model.addAttribute("pizza", pizza);
        model.addAttribute("sizes", sizes);
        model.addAttribute("companies", companyService.getCompanies());

        return "pizza/pizza-form";
    }
}
