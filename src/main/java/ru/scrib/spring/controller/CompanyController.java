package ru.scrib.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.scrib.spring.entity.pizza.Company;
import ru.scrib.spring.service.CompanyService;
import ru.scrib.spring.string.StringHelper;


@Controller
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping("/add")
    public String addPizza(Model model) {
        Company company = new Company();
        model.addAttribute("company", company);
        return "company-form";
    }

    @PostMapping("/save")
    public String savePizza(@ModelAttribute("company") Company company) {
        System.out.println(company.getName());
        company.setName(StringHelper.convertFromUTF8(company.getName()));

        companyService.saveCompany(company);
        return "redirect:/pizza/list";
    }
}
