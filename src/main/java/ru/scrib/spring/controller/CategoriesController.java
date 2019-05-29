package ru.scrib.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.scrib.spring.entity.pizza.CategoryIngredient;
import ru.scrib.spring.entity.pizza.Ingredient;
import ru.scrib.spring.service.CategoryIngredientService;
import ru.scrib.spring.string.StringHelper;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoriesController {

    @Autowired
    private CategoryIngredientService categoryIngredientService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("categories", categoryIngredientService.getCategories());
        return "categories/category-list";
    }

    @GetMapping("/add")
    public String addCategory(Model model){
        CategoryIngredient categoryIngredient = new CategoryIngredient();
        model.addAttribute("category", categoryIngredient);
        return "categories/category-form";
    }

    @PostMapping("/save")
    public String saveCategory(@ModelAttribute("category") CategoryIngredient category) {
        category.setName(StringHelper.convertFromUTF8(category.getName()));
        categoryIngredientService.saveCategory(category);
        return "redirect:/categories/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("categoryId") long id){
        categoryIngredientService.deleteCategory(id);
        return "redirect:/categories/list";
    }

    @GetMapping("/update")
    public String update(@RequestParam("categoryId") long id, Model model){
        CategoryIngredient category = categoryIngredientService.getCategory(id);
        model.addAttribute("category",category);
        return "categories/category-form";
    }

    @GetMapping("/info")
    public String info(@RequestParam("categoryId") long id, Model model){
        List<Ingredient> ingredients = categoryIngredientService.getCategory(id).getIngredients();
        model.addAttribute("ingredients", ingredients);
        model.addAttribute("category", categoryIngredientService.getCategory(id));
        return "categories/category-info";
    }
}
