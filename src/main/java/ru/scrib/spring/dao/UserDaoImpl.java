package ru.scrib.spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.scrib.spring.entity.UserApp;

import java.util.logging.Logger;

@Repository
public class UserDaoImpl implements UserDAO {

    // need to inject the session factory
    @Autowired
    private SessionFactory sessionFactory;

    private Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public UserApp findByUserName(String theUserName) {
        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        logger.info("Find by username");
        // now retrieve/read from database using username
        Query<UserApp> theQuery = currentSession.createQuery("from UserApp where userName=:uName", UserApp.class);
        theQuery.setParameter("uName", theUserName);
        UserApp theUserApp = null;
        try {
            theUserApp = theQuery.getSingleResult();
        } catch (Exception e) {
            theUserApp = null;
        }

        return theUserApp;
    }

    @Override
    public void save(UserApp theUserApp) {
        // get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // create the user ... finally LOL
        currentSession.saveOrUpdate(theUserApp);
    }

}
