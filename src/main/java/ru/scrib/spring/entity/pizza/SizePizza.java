package ru.scrib.spring.entity.pizza;

public enum SizePizza {
    BIG("Большая"), MEDIUM("Средняя"), STANDART("Обычная");
    private String name;

    SizePizza(String name) {
        this.name = name;
    }

    public static String[] getNames() {
        String[] str = new String[3];
        str[0] = "BIG";
        str[1] = "MEDIUM";
        str[2] = "STANDART";
        return str;
    }

    public static SizePizza getType(String s) {
        if (s.equals("Большая"))
            return BIG;
        else if (s.equals("Средняя"))
            return MEDIUM;
        return STANDART;
    }

    public String getName() {
        return name;
    }
}
