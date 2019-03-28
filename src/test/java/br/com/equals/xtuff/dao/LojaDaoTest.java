package br.com.equals.xtuff.dao;

import br.com.equals.xtuff.dao.LojaDao;
import br.com.equals.xtuff.domain.entities.Loja;
import br.com.equals.xtuff.domain.entities.User;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class LojaDaoTest {
    private static EntityManager em = null;
    private static LojaDao dao = null;
    private static int idUser;
    private static UserDao userDao = null;

    @BeforeAll
    public static void setup() {
        em = Persistence.createEntityManagerFactory("xtuff").createEntityManager();
        dao = new LojaDao(em);
        User user = new User("Sid","Rezende","sid@equals.com.br","123");
        userDao = new UserDao(em);
        idUser= userDao.add(user).getId();
    }


    @BeforeEach
    public void wipeOut() {
        dao.deleteAll();
    }

    @AfterAll
    public static void shutDown() {
        em.close();
    }

    @Test
    public void uma_loja_deve_ser_adicionada() {

        try {
            User user = userDao.getById(idUser);
            Loja loja = new Loja(null, "Teste", "sid@teste.com", "123", user) ;
            int addedId = dao.add(loja).getId();
            dao.commit();
            loja.setUser(user);
            userDao.commit();
            dao.commit();
            Loja loja1 = dao.getById(addedId);
            Assertions.assertTrue(loja.getEmail().equals(loja1.getEmail()) && loja.getNome().equals(loja1.getNome()));

        } catch (Exception e) {

        }
    }

    @Test
    public void multiplas_lojas_deve_ser_adicionadas() {
        try {
            for (int i = 0; i < 100; i++) {
                Loja loja = new Loja(null, "Teste", "sid@teste.com", "123");
                int addedId = dao.add(loja).getId();
                dao.commit();
            }
            List<Loja> all = dao.getAll();
            Assertions.assertTrue(all.size() == 100);

        } catch (Exception e) {

        }
    }

    @Test
    public void dado_um_id_uma_loja_deve_ser_removida() {
        EntityManager em = null;
        try {
            Loja loja = new Loja(null, "Teste", "sid@teste.com", "123");
            int addedId = dao.add(loja).getId();
            dao.commit();
            dao.deleteById(addedId);
            dao.commit();
            List<Loja> all = dao.getAll();
            Assertions.assertTrue(all.size()==0);

        } catch (Exception e) {

        }
    }
}

