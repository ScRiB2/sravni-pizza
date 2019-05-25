package ru.scrib.spring.entity.pizza;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "company", cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Pizza> pizzas;

    public Company() {
    }

    public Company(String name) {
        this.name = name;
    }

    public void addPizzza(Pizza tempPizza) {
        if (pizzas == null) {
            pizzas = new ArrayList<>();
        }
        pizzas.add(tempPizza);
        tempPizza.setCompany(this);
    }
}
