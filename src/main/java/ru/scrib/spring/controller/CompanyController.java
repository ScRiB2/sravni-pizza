package ru.scrib.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.scrib.spring.entity.pizza.Company;
import ru.scrib.spring.entity.pizza.Pizza;
import ru.scrib.spring.service.CompanyService;
import ru.scrib.spring.string.StringHelper;

import java.util.List;


@Controller
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("company", companyService.getCompanies());
        return "company/company-list";
    }

    @GetMapping("/add")
    public String addCompany(Model model) {
        Company company = new Company();
        model.addAttribute("company", company);
        return "company/company-form";
    }

    @PostMapping("/save")
    public String saveCompany(@ModelAttribute("company") Company company) {
        company.setName(StringHelper.convertFromUTF8(company.getName()));
        companyService.saveCompany(company);
        return "redirect:/company/list";
    }

    @GetMapping("/delete")
    public String deleteCompany(@RequestParam("companyId") long id){
        companyService.deleteCompany(id);
        return "redirect:/company/list";
    }

    @GetMapping("/update")
    public String updateCompany(@RequestParam("companyId") long id, Model model){
        Company company = companyService.getCompany(id);
        model.addAttribute("company",company);
        return "company/company-form";
    }

    @GetMapping("/info")
    public String infoCompany(@RequestParam("companyId") long id, Model model){
        List<Pizza> pizzas = companyService.getCompany(id).getPizzas();
        model.addAttribute("pizzas", pizzas);
        model.addAttribute("company", companyService.getCompany(id));
        return "company/company-info";
    }
}
