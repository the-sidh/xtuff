package br.com.equals.xtuff.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import br.com.equals.xtuff.domain.entities.Comerciante;

@Repository
public class ComercianteDao extends HibernateGenericDao<Comerciante> {

    public ComercianteDao(EntityManager em) {
        super(em);
    }

    public ComercianteDao() {
        super();
    }

    public Comerciante findByUsername(String email) {
        Comerciante comerciante = null;
        try {
            comerciante = (Comerciante) this.em
                    .createQuery(
                            "select c from Comerciante c where "
                                    + "c.email = :email")
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception e) {

        }
        return comerciante;
    }
}
