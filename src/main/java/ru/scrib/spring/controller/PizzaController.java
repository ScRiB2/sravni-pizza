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

import javax.annotation.PostConstruct;
import java.util.*;

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
    public String addPizza(Model model) {
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("sizes", sizes);
        model.addAttribute("companies", companyService.getCompanies());
        model.addAttribute("ingredients", ingredientService.getIngredients());
        return "pizza/pizza-form";
    }

    @PostMapping("/savePizza")
    public String savePizza(@ModelAttribute("pizza") Pizza pizza) {
        pizzaService.savePizza(pizza);
        return "redirect:/pizza/list";
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

    @GetMapping("/addIngredient")
    public String addIngredientForPizza(@ModelAttribute("pizza") Pizza pizza, Model model){
        model.addAttribute("ingredientsInDb", ingredientService.getIngredients());
        model.addAttribute("pizza", pizza);
        return "pizza/pizza-ingredient";
    }

    @PostMapping("/addPizza")
    public String addPostPizza(@ModelAttribute("pizza") Pizza pizza, Model model) {
        List<Ingredient> ingredients = pizza.getIngredients();
        List<Ingredient> newIngredients = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            newIngredients.add(ingredientService.getIngredient(Long.parseLong(ingredient.getName())));
        }
        pizza.setIngredients(newIngredients);
        model.addAttribute("sizes", sizes);
        model.addAttribute("companies", companyService.getCompanies());

        return "pizza/pizza-form";
    }
}
