package ru.scrib.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.scrib.spring.entity.pizza.Company;
import ru.scrib.spring.entity.pizza.Pizza;
import ru.scrib.spring.entity.pizza.SizePizza;
import ru.scrib.spring.service.CompanyService;
import ru.scrib.spring.service.PizzaService;
import ru.scrib.spring.string.StringHelper;

import javax.annotation.PostConstruct;
import java.util.*;

@Controller
@RequestMapping("/pizza")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private CompanyService companyService;

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
        List<Pizza> pizzas = pizzaService.getPizzas();
        theModel.addAttribute("pizzas", pizzas);
        return "list-pizzas";
    }

    @GetMapping("/addPizza")
    public String addPizza(Model model) {
        Pizza pizza = new Pizza();
        companies = companyService.getCompanies();
        model.addAttribute("pizza", pizza);
        model.addAttribute("sizes", sizes);
        model.addAttribute("companies", companies);
        return "pizza-form";
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
        Pizza pizza = pizzaService.getPizza(id);
        companies = companyService.getCompanies();
        model.addAttribute("pizza", pizza);
        model.addAttribute("sizes", sizes);
        model.addAttribute("companies", companies);
        return "pizza-form";
    }

    @GetMapping("/info")
    public String infoPizza(@RequestParam("pizzaId") long id,
                            Model model) {
        Pizza pizza = pizzaService.getPizza(id);
        model.addAttribute("pizza", pizza);
        return "pizza-info";
    }
}
