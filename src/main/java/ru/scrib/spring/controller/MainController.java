package ru.scrib.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.scrib.spring.filters.Filters;
import ru.scrib.spring.service.CompanyService;
import ru.scrib.spring.service.PizzaService;

@Controller
public class MainController {

    @GetMapping("/access-denied")
    public String accessDeniedPage() {
        return "access-denied";
    }

    @Autowired
    private CompanyService companyService;

    @Autowired
    private PizzaService pizzaService;

    private Integer[] prices;

    @GetMapping("/")
    public String list(Model model) {
        Filters filters = new Filters();
        filters.setMinPrice(pizzaService.getMinPrice());
        filters.setMaxPrice(pizzaService.getMaxPrice());
        filters.setSort(0);
        model.addAttribute("pizzas", pizzaService.getPizzasWithFilters(filters));
        model.addAttribute("filters", filters);
        model.addAttribute("companies", companyService.getCompanies());
        return "catalog/catalog-list";
    }

    @PostMapping("/")
    public String activateFilter(@ModelAttribute("filters") Filters filters,
                                 Model model) {
        model.addAttribute("pizzas", pizzaService.getPizzasWithFilters(filters));
        model.addAttribute("filters", filters);
        model.addAttribute("companies", companyService.getCompanies());
        return "catalog/catalog-list";
    }
}
