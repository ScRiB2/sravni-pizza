package ru.scrib.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.scrib.spring.entity.pizza.Pizza;
import ru.scrib.spring.entity.pizza.SizePizza;
import ru.scrib.spring.service.PizzaService;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pizza")
public class PizzaController {
    @Autowired
    private PizzaService pizzaService;

    private Map<SizePizza, String> sizes;

    @PostConstruct
    protected void loadSizes() {
        sizes = new LinkedHashMap<>();

        sizes.put(SizePizza.BIG, "Big");
        sizes.put(SizePizza.MEDIUM, "MEDIUM");
        sizes.put(SizePizza.STANDART, "STANDART");
    }

    @GetMapping("/list")
    public String listCustomers(Model theModel) {
        List<Pizza> pizzas = pizzaService.getPizzas();
        theModel.addAttribute("pizzas", pizzas);
        return "list-pizzas";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Pizza pizza = new Pizza();
        model.addAttribute("pizza", pizza);
        model.addAttribute("sizes", sizes);
        return "pizza-form";
    }

    @PostMapping("/savePizza")
    public String savePizza(@ModelAttribute("pizza") Pizza pizza) {
        pizzaService.savePizza(pizza);
        return "redirect:/pizza/list";
    }
}
