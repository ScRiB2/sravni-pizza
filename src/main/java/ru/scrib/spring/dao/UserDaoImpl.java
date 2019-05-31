package ru.scrib.spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.scrib.spring.entity.user.User;

import java.util.logging.Logger;

@Repository
public class UserDaoImpl implements UserDAO {

    // need to inject the session factory
    @Autowired
    private SessionFactory sessionFactory;

    private Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public User findByUserName(String theUserName) {
        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        Query<User> theQuery = currentSession.createQuery("from User where userName=:uName", User.class);
        theQuery.setParameter("uName", theUserName);
        User theUser = null;
        try {
            theUser = theQuery.getSingleResult();
        } catch (Exception e) {
            theUser = null;
        }
        return theUser;
    }

    @Override
    public void save(User theUser) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(theUser);
    }

}
