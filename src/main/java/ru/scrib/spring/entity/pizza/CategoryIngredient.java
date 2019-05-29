package ru.scrib.spring.entity.pizza;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "category_ingredient")
public class CategoryIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "categoryIngredient", cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    List<Ingredient> ingredients;

    public CategoryIngredient() {
    }

    public CategoryIngredient(String name) {
        this.name = name;
    }

    public void addIngredient(Ingredient ingredient){
        if(ingredients == null)
            ingredients = new ArrayList<>();
        ingredients.add(ingredient);
        ingredient.setCategoryIngredient(this);
    }

    @Override
    public String toString() {
        return "CategoryIngredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
