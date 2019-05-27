package ru.scrib.spring.filters;

import lombok.Getter;
import lombok.Setter;
import ru.scrib.spring.entity.pizza.Company;

@Getter
@Setter
public class CompanyFilter {
    private Company company;
    private boolean isChecked;

    public CompanyFilter(Company company, boolean isChecked) {
        this.company = company;
        this.isChecked = isChecked;
    }
}
