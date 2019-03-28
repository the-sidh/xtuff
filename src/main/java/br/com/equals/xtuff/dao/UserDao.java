package br.com.equals.xtuff.dao;

import javax.persistence.EntityManager;

import br.com.equals.xtuff.domain.entities.User;

public class UserDao  extends  HibernateGenericDao<User>{

    public UserDao(EntityManager em) {
        super(em);
    }
}
