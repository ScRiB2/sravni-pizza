package ru.scrib.spring.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.type.IntegerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.scrib.spring.entity.pizza.Company;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ComanyDaoImpl implements CompanyDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Company> getCompanies() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Company> query = currentSession.createQuery("from Company order by name", Company.class);
        return query.getResultList();
    }

    @Override
    public void saveCompany(Company company) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(company);
    }

    @Override
    public void deleteCompany(long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("delete from Company where id=:companyId");
        query.setParameter("companyId", id);
        query.executeUpdate();
    }

    @Override
    public Company getCompany(long id) {
        return sessionFactory.getCurrentSession().get(Company.class, id);
    }

    @Override
    public List<Company> getCompaniesByName(String[] companiesName) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Company> query;
        if(companiesName == null || companiesName.length == 0)
            query = currentSession.createQuery("from Company");
        else {
            query = currentSession.createQuery("from Company where name in (:n) ");
            query.setParameterList("n", companiesName);
        }
        return query.getResultList();
    }
}
