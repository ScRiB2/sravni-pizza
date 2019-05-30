package ru.scrib.spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.scrib.spring.entity.pizza.CategoryIngredient;

import java.util.List;

@Repository
public class CategoryIngredientDaoImpl implements CategoryIngredientDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<CategoryIngredient> getCategories() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<CategoryIngredient> query = currentSession.createQuery("from CategoryIngredient order by name", CategoryIngredient.class);
        return query.getResultList();
    }

    @Override
    public void saveCategory(CategoryIngredient categoryIngredient) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(categoryIngredient);
    }

    @Override
    public void deleteCategory(long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("delete from CategoryIngredient where id=:CategoryIngredientId");
        query.setParameter("CategoryIngredientId", id);
        query.executeUpdate();
    }

    @Override
    public CategoryIngredient getCategory(long id) {
        return sessionFactory.getCurrentSession().get(CategoryIngredient.class, id);
    }

    @Override
    public List<CategoryIngredient> getCategoriesByName(String[] categoryName) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<CategoryIngredient> query;
        if (categoryName == null || categoryName.length == 0)
            query = currentSession.createQuery("from CategoryIngredient");
        else {
            query = currentSession.createQuery("from CategoryIngredient where name in (:n) ");
            query.setParameterList("n", categoryName);
        }
        return query.getResultList();
    }
}
