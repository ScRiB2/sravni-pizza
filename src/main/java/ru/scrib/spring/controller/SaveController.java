package ru.scrib.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.scrib.spring.entity.pizza.Pizza;
import ru.scrib.spring.service.CompanyService;
import ru.scrib.spring.service.IngredientService;
import ru.scrib.spring.service.PizzaService;
import ru.scrib.spring.string.StringHelper;

@Controller
@RequestMapping("/pizza/savePizza")
public class SaveController {

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private IngredientService ingredientService;

    @RequestMapping(params = "save", method = RequestMethod.POST)
    public String savePizza(@ModelAttribute("pizza") Pizza pizza) {
        pizzaService.savePizza(pizza);
        return "redirect:/pizza/list";
    }

    @RequestMapping(params = "add-ingredient", method = RequestMethod.POST)
    public String addIngredientForPizza(@ModelAttribute("pizza") Pizza pizza, Model model) {
        pizza.setName(StringHelper.convertFromUTF8(pizza.getName()));
        model.addAttribute("ingredientsInDb", ingredientService.getIngredients());
        model.addAttribute("pizza", pizza);
        return "/pizza/pizza-ingredient";
    }
}
