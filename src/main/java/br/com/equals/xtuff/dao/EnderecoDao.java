package br.com.equals.xtuff.dao;

import javax.persistence.EntityManager;

import br.com.equals.xtuff.domain.entities.Endereco;
import br.com.equals.xtuff.domain.entities.User;

public class EnderecoDao extends  HibernateGenericDao<Endereco>{

    public EnderecoDao(EntityManager em) {
        super(em);
    }
}
