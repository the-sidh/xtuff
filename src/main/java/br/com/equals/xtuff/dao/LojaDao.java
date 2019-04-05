package br.com.equals.xtuff.dao;

import org.springframework.stereotype.Repository;

import br.com.equals.xtuff.domain.entities.Loja;

import javax.persistence.EntityManager;

@Repository
public class LojaDao extends HibernateGenericDao<Loja>{
    public LojaDao(EntityManager em) {
        super(em);
    }
    public LojaDao() {super();}
}
