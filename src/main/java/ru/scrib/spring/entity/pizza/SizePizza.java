package ru.scrib.spring.entity.pizza;

public enum SizePizza {
    BIG("Большая"), MEDIUM("Средняя"), STANDART("Обычная");
    private String name;
    SizePizza(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
