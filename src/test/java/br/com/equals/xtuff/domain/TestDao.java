package br.com.equals.xtuff.domain;

import br.com.equals.xtuff.dao.LojaDao;
import br.com.equals.xtuff.domain.entities.Loja;
import br.com.equals.xtuff.domain.entities.Produto;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Calendar;

public class TestDao {

    @Test
    public void Test() {
        EntityManager em = null;
        try {
            em = Persistence.createEntityManagerFactory("xtuff").createEntityManager();
            LojaDao dao = new LojaDao(em);

            Loja loja = new Loja(100, "Teste", "sid@teste.com", "123");
            dao.add(loja);
            dao.commit();

            Loja loja1 = dao.getById(100);
            assert (loja.equals(loja1));


        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            if (em != null) {
                em.close();
            }
            System.exit(0);
        }

    }
}

