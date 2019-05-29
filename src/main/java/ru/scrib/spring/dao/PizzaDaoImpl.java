package ru.scrib.spring.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.type.IntegerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.scrib.spring.entity.pizza.CategoryIngredient;
import ru.scrib.spring.entity.pizza.Ingredient;
import ru.scrib.spring.entity.pizza.Pizza;
import ru.scrib.spring.entity.pizza.SizePizza;
import ru.scrib.spring.filters.Filters;

import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class PizzaDaoImpl implements PizzaDao {
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
        currentSession.clear();
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

    @Override
    public Pizza getPizza(long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Pizza pizza = currentSession.get(Pizza.class, id);
        return pizza;
    }

    @Override
    public int getMinPrice() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Integer> query = currentSession
                .createSQLQuery("select min(price) as p from Pizza")
                .addScalar("p", IntegerType.INSTANCE);
        return query.getSingleResult();
    }

    @Override
    public int getMaxPrice() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Integer> query = currentSession
                .createSQLQuery("select max(price) as p from Pizza")
                .addScalar("p", IntegerType.INSTANCE);
        return query.getSingleResult();
    }

    @Override
    public List<Pizza> getPizzasWithFilters(Filters filters) {
        Session currentSession = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = currentSession.getCriteriaBuilder();
        CriteriaQuery<Pizza> pizzaCriteria = cb.createQuery(Pizza.class);
        Root<Pizza> pizzaRoot = pizzaCriteria.from(Pizza.class);

        pizzaCriteria.select(pizzaRoot);
        Predicate[] predicates = new Predicate[4];
        predicates[0] = cb.between(pizzaRoot.get("price"), filters.getMinPrice(), filters.getMaxPrice());

        Expression<String> exp = pizzaRoot.get("company");
        predicates[1]  = exp.in(filters.getCompanyList());

        Expression<SizePizza> expr = pizzaRoot.get("size");
        predicates[2]  = expr.in(filters.getSizePizzas());

        Join<Pizza, Ingredient> ingredient = pizzaRoot.join("ingredients");
        Expression<Ingredient> expres = ingredient.get("name");
        predicates[3] = expres.in(filters.getIngredients());

        pizzaCriteria.where(predicates);

        Query<Pizza> query = currentSession.createQuery(pizzaCriteria);
        return query.getResultList();
    }
}
