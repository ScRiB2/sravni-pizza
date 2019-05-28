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
import ru.scrib.spring.entity.pizza.Pizza;
import ru.scrib.spring.filters.Filters;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
        Criteria criteria = currentSession.createCriteria(Pizza.class);
        switch (filters.getSort()){
            case 0:
                criteria.addOrder(Order.asc("price"));
                break;
            case 1:
                criteria.addOrder(Order.desc("price"));
                break;
            case 2:
                criteria.addOrder(Order.asc("name"));
                break;
            case 3:
                criteria.addOrder(Order.desc("name"));
                break;
        }
        criteria.add(Restrictions.between("price", filters.getMinPrice(), filters.getMaxPrice()));
        criteria.add(Restrictions.in("company", filters.getCompanyList()));
        //criteria.add(Restrictions.allEq())
        return criteria.list();
    }
}
