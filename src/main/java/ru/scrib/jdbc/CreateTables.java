package ru.scrib.jdbc;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.scrib.spring.entity.Role;
import ru.scrib.spring.entity.User;


public class CreateTables {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Role.class)
                .buildSessionFactory();
        factory.close();
    }
}
