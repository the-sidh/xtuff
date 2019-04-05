package br.com.equals.xtuff.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import br.com.equals.xtuff.domain.entities.Produto;

@Repository
public class ProdutoDao extends HibernateGenericDao<Produto>{
    public ProdutoDao(EntityManager em) {
        super(em);
    }
    public ProdutoDao() {super();}
}
