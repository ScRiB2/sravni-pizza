package ru.scrib.spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.scrib.spring.entity.pizza.Pizza;

import java.util.List;

@Repository
public class PizzaDaoImpl implements PizzaDao{
    @Autowired
    private SessionFactory sessionFactory;

    public List<Pizza> getPizzas() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Pizza> theQuery = currentSession.createQuery("from Pizza order by name", Pizza.class);
        List<Pizza> pizzas = theQuery.getResultList();
        return pizzas;
    }

    public void savePizza(Pizza pizza) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(pizza);
    }

    @Override
    public void deletePizza(long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query theQuery =
                currentSession.createQuery("delete from Pizza  where id=:pizzaId");
        theQuery.setParameter("pizzaId", id);
        theQuery.executeUpdate();
    }
}
