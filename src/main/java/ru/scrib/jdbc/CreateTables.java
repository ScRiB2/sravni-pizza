package ru.scrib.jdbc;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.scrib.spring.entity.Role;
import ru.scrib.spring.entity.UserApp;


public class CreateTables {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(UserApp.class)
                .addAnnotatedClass(Role.class)
                .buildSessionFactory();
        factory.close();
    }
}
