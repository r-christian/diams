package com.christian.diams.dao;

import com.christian.diams.dao.base.AbstractGenericDAO;
import com.christian.diams.model.AssetType;
import com.christian.diams.model.Company;
import com.christian.diams.model.User;

import javax.persistence.TypedQuery;
import java.util.List;

public class CompanyDAO extends AbstractGenericDAO<Company> {
    public CompanyDAO() {
        super(Company.class);
    }

    public Company getByName(String name){
        Company company = null;
        entityManager.getTransaction().begin();
        String jpql = "select c from Company c where c.name = :name";
        TypedQuery<Company> query = entityManager.createQuery(jpql, Company.class);
        query.setParameter("name", name);
        try {
            company = query.getSingleResult();
        } finally {
            entityManager.getTransaction().commit();
            return company;
        }
    }

    public List<Company> getCompanyNames(){
        String jpql = "select c.name from Company c";
        List<Company> companyList = (List<Company>)entityManager.createQuery(jpql) .getResultList();
        return companyList;
    }
}
