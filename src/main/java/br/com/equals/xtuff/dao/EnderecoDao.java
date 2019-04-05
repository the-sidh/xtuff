package br.com.equals.xtuff.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import br.com.equals.xtuff.domain.entities.Endereco;

@Repository
public class EnderecoDao extends  HibernateGenericDao<Endereco>{

    public EnderecoDao(EntityManager em) {
        super(em);
    }

    public EnderecoDao() {super();}


}
