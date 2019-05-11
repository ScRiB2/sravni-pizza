package ru.scrib.jdbc;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateTables {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                //.addAnnotatedClass(AppUser.class)
                .buildSessionFactory();
        factory.close();
    }
}
