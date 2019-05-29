package ru.scrib.spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.scrib.spring.entity.pizza.Ingredient;

import java.util.List;

@Repository
public class IngredientDaoImpl implements IngredientDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Ingredient> getIngredients() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Ingredient> query = currentSession.createQuery("from Ingredient order by name", Ingredient.class);
        return query.getResultList();
    }

    @Override
    public void saveIngredient(Ingredient ingredient) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(ingredient);
    }

    @Override
    public void deleteIngredient(long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("delete from Ingredient where id=:ingredientId");
        query.setParameter("ingredientId", id);
        query.executeUpdate();
    }

    @Override
    public Ingredient getIngredient(long id) {
        return sessionFactory.getCurrentSession().get(Ingredient.class, id);
    }
}
