package ru.scrib.spring.filters;

import lombok.Getter;
import lombok.Setter;
import ru.scrib.spring.string.StringHelper;


@Getter
@Setter
public class Filters {
    private int minPrice;
    private int maxPrice;
    private int sort;
    private String[] companies;

    public Filters() {
    }

    public void setCompanies(String[] companies) {
        String[] newCompanies = new String[companies.length];
        for (int i = 0; i < companies.length; i++) {
            newCompanies[i] = StringHelper.convertFromUTF8(companies[i]);
        }
        this.companies = newCompanies;
    }

}
