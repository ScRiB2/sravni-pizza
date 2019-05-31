package ru.scrib.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.scrib.spring.entity.pizza.Ingredient;
import ru.scrib.spring.service.CategoryIngredientService;
import ru.scrib.spring.service.IngredientService;
import ru.scrib.spring.string.StringHelper;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private CategoryIngredientService categoryIngredientService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("ingredients", ingredientService.getIngredients());
        return "ingredients/ingredient-list";
    }

    @GetMapping("/add")
    public String addCategory(Model model) {
        Ingredient ingredient = new Ingredient();
        model.addAttribute("ingredient", ingredient);
        model.addAttribute("categories", categoryIngredientService.getCategories());
        return "ingredients/ingredient-form";
    }

    @PostMapping("/save")
    public String saveCategory(@ModelAttribute("ingredient") Ingredient ingredient) {
        ingredient.setName(StringHelper.convertFromUTF8(ingredient.getName()));
        ingredientService.saveIngredient(ingredient);
        return "redirect:/ingredients/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("ingredientId") long id) {
        ingredientService.deleteIngredient(id);
        return "redirect:/ingredients/list";
    }

    @GetMapping("/update")
    public String update(@RequestParam("ingredientId") long id, Model model) {
        Ingredient ingredient = ingredientService.getIngredient(id);
        model.addAttribute("ingredient", ingredient);
        model.addAttribute("categories", categoryIngredientService.getCategories());
        return "ingredients/ingredient-form";
    }
}
