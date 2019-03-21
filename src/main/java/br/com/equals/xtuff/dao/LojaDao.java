package br.com.equals.xtuff.dao;

import br.com.equals.xtuff.domain.entities.Loja;

import javax.persistence.EntityManager;

public class LojaDao extends HibernateGenericDao<Loja>{
    public LojaDao(EntityManager em) {
        super(em);
    }
}
