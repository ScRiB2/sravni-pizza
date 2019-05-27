package ru.scrib.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.scrib.spring.entity.pizza.Company;
import ru.scrib.spring.entity.pizza.Pizza;
import ru.scrib.spring.filters.CompanyFilter;
import ru.scrib.spring.filters.Filters;
import ru.scrib.spring.service.CompanyService;
import ru.scrib.spring.service.PizzaService;
import ru.scrib.spring.string.StringHelper;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/catalog")
public class CatalogController {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private PizzaService pizzaService;

    @GetMapping("/list")
    public String list(Model model) {
        Filters filters = new Filters();
        List<CompanyFilter> companyFilters= new ArrayList<>();
        List<Company> companies = companyService.getCompanies();

        filters.setCompanies(new String[]{"Yummy"});
        filters.setMinPrice(0);
        filters.setMaxPrice(1000);
        filters.setSort(0);
        model.addAttribute("pizzas",pizzaService.getPizzas());
        model.addAttribute("filters", filters);
        model.addAttribute("companies", companyService.getCompanies());
        return "catalog/catalog-list";
    }

    @PostMapping("/list")
    public String activateFilter(@ModelAttribute("filters") Filters filters,
                                 Model model) {
        model.addAttribute("pizzas",pizzaService.getPizzas());
        model.addAttribute("filters", filters);
        model.addAttribute("companies", companyService.getCompanies());
        return "catalog/catalog-list";
    }
}
