package ru.scrib.spring.filters;

import lombok.Getter;
import lombok.Setter;
import ru.scrib.spring.entity.pizza.Company;
import ru.scrib.spring.entity.pizza.SizePizza;
import ru.scrib.spring.string.StringHelper;

import java.util.List;
import java.util.Set;


@Getter
@Setter
public class Filters {
    private int minPrice;
    private int maxPrice;
    private String[] companiesName;
    private List<Company> companyList;
    private String[] sizes;
    private String[] categoriesName;
    private List<String> ingredients;
    private SizePizza[] sizePizzas;
    private Set<String> categories;

    public Filters() {
    }

    public void setCompaniesName(String[] companiesName) {
        String[] newCompanies = new String[companiesName.length];
        for (int i = 0; i < companiesName.length; i++) {
            newCompanies[i] = StringHelper.convertFromUTF8(companiesName[i]);
        }
        this.companiesName = newCompanies;
    }

    public void setSizes(String[] sizes) {
        String[] newSizes = new String[sizes.length];
        for (int i = 0; i < sizes.length; i++) {
            newSizes[i] = StringHelper.convertFromUTF8(sizes[i]);
        }
        this.sizes = newSizes;
    }

    public void setCategoriesName(String[] categoriesName) {
        String[] newCategories = new String[categoriesName.length];
        for (int i = 0; i < categoriesName.length; i++) {
            newCategories[i] = StringHelper.convertFromUTF8(categoriesName[i]);
        }
        this.categoriesName = newCategories;
    }
}
