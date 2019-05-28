package ru.scrib.spring.filters;

import lombok.Getter;
import lombok.Setter;
import ru.scrib.spring.entity.pizza.Company;
import ru.scrib.spring.string.StringHelper;

import java.util.List;


@Getter
@Setter
public class Filters {
    private int minPrice;
    private int maxPrice;
    private int sort;
    private String[] companiesName;
    private List<Company> companyList;

    public Filters() {
    }

    public void setCompaniesName(String[] companiesName) {
        String[] newCompanies = new String[companiesName.length];
        for (int i = 0; i < companiesName.length; i++) {
            newCompanies[i] = StringHelper.convertFromUTF8(companiesName[i]);
        }
        this.companiesName = newCompanies;
    }

}
