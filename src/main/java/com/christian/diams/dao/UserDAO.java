package com.christian.diams.dao;

import com.christian.diams.dao.base.AbstractGenericDAO;
import com.christian.diams.model.User;

import javax.persistence.TypedQuery;

public class UserDAO extends AbstractGenericDAO<User> {

    public UserDAO() {
        super(User.class);
    }

    protected User getUserByEmail(String email){
        User user = null;
        entityManager.getTransaction().begin();
        String jpql = "select u from User u where u.email = :email";
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
        query.setParameter("email", email);
        try {
            user = query.getSingleResult();
        } finally {
            entityManager.getTransaction().commit();
            return user;
        }
    }

    public boolean authenticateUser(String email, String password){
        User user = getUserByEmail(email);
        if(user != null){
            if(password.contentEquals(password)){
                return true;
            }
        }
        return false;
    }

    public User getAuthenticateUser(String email, String password){
        User user = getUserByEmail(email);
        if(user != null){
            if(user.getPassword().contentEquals(password)){
                return user;
            }
        }
        return null;
    }
}
