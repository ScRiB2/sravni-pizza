package ru.scrib.spring.entity.pizza;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "pizza")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "price")
    private int price;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "size")
    @Enumerated(EnumType.STRING)
    private SizePizza size;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "pizza_ingredient",
            joinColumns = @JoinColumn(name = "pizza_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> ingredients;

    public Pizza() {
    }

    public Pizza(String name) {
        this.name = name;
    }

    public Pizza(String name, String image, int price, Company company, SizePizza sizePizza) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.company = company;
        this.size = sizePizza;
    }

    public void addIngredient(Ingredient ingredient) {
        if (ingredients == null) {
            ingredients = new ArrayList<>();
        }
        ingredients.add(ingredient);
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", company=" + company +
                ", size=" + size +
                '}';
    }
}


