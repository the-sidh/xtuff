package br.com.equals.xtuff.dao;

import br.com.equals.xtuff.domain.entities.Endereco;
import br.com.equals.xtuff.domain.entities.Loja;
import br.com.equals.xtuff.domain.entities.Comerciante;

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
    private static ComercianteDao comercianteDao = null;
    private static EnderecoDao endDao = null;
    private static int idEnd;

    @BeforeAll
    public static void setup() {
        em = Persistence.createEntityManagerFactory("xtufftest").createEntityManager();
        dao = new LojaDao(em);
        Endereco endereco = new Endereco("Avenida Dom Pedro I, 500" , "Ipiranga","300","cj30","Sao Paulo", "SP", "01549-000");
        endDao = new EnderecoDao(em);
        idEnd  = endDao.add(endereco).getId();
        try {
            endDao.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

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
            Endereco endereco = endDao.getById(idEnd);
            Loja loja = new Loja("Teste") ;
            loja.setEndereco(endereco);
            int addedId = dao.add(loja).getId();
            dao.commit();
            Loja loja1 = dao.getById(addedId);
            Assertions.assertTrue(loja1.getNome().equals("Teste"));

        } catch (Exception e) {

        }
    }

    @Test
    public void multiplas_lojas_deve_ser_adicionadas() {
        Comerciante user = comercianteDao.getById(idUser);
        Endereco endereco = endDao.getById(idEnd);
        try {
            for (int i = 0; i < 100; i++) {
                Loja loja = new Loja("Teste");
                loja.setEndereco(endereco);
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
        Comerciante user = comercianteDao.getById(idUser);
        Endereco endereco = endDao.getById(idEnd);
        EntityManager em = null;
        try {
            Loja loja = new Loja( "Teste");
            loja.setEndereco(endereco);
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

