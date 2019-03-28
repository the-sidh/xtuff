package br.com.equals.xtuff.dao;

import javax.persistence.EntityManager;

import br.com.equals.xtuff.domain.entities.Produto;

public class ProdutoDao extends HibernateGenericDao<Produto>{
    public ProdutoDao(EntityManager em) {
        super(em);
    }
}
