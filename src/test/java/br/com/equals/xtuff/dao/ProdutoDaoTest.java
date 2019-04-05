package br.com.equals.xtuff.dao;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.equals.xtuff.domain.entities.Endereco;
import br.com.equals.xtuff.domain.entities.Loja;
import br.com.equals.xtuff.domain.entities.Produto;
import br.com.equals.xtuff.domain.entities.Comerciante;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ProdutoDaoTest {
    private static EntityManager em = null;
    private static ProdutoDao dao = null;
    private static int idLoja;
    private static LojaDao lojaDao = null;

    @BeforeAll
    public static void setup() {
        em = Persistence.createEntityManagerFactory("xtufftest").createEntityManager();

        Comerciante user = new Comerciante("Sid","Rezende","sid@equals.com.br","123");
        ComercianteDao comercianteDao = new ComercianteDao(em);
        Comerciante userPersistido  = comercianteDao.add(user);
        try {
            comercianteDao.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Endereco endereco = new Endereco("Avenida Dom Pedro I, 500" , "Ipiranga","300","cj30","Sao Paulo", "SP", "01549-000");
        EnderecoDao endDao = new EnderecoDao(em);
        Endereco enderecoPersistido  = endDao.add(endereco);
        try {
            endDao.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        dao = new ProdutoDao(em);
        Loja loja = new Loja("Teste");
        loja.setEndereco(enderecoPersistido);
        lojaDao = new LojaDao(em);
        idLoja = lojaDao.add(loja).getId();
        try {
            lojaDao.commit();
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
    public void um_produto_deve_ser_adicionado() {

        try {
            Loja loja = lojaDao.getById(idLoja);
            Produto produto = new Produto(null, "Parafuso", 12.3, Calendar.getInstance(), Calendar.getInstance(), 10, loja);
            loja.getProdutos().add(produto);
            lojaDao.commit();
            dao.add(produto);
            dao.commit();

            List<Produto> all = dao.getAll();
            Assertions.assertTrue(all.size() == 1);

        } catch (Exception e) {

        }
    }

    @Test
    public void multiplos_produtos_devem_ser_adicionados() {
        try {
            for (int i = 0; i < 100; i++) {
                Produto produto = new Produto(null, "Parafuso", 12.3, Calendar.getInstance(), Calendar.getInstance(), 10);
                int addedId = dao.add(produto).getId();
                dao.commit();
            }
            List<Produto> all = dao.getAll();
            Assertions.assertTrue(all.size() == 100);

        } catch (Exception e) {

        }
    }

    @Test
    public void dado_um_id_um_produto_deve_ser_removido() {
        EntityManager em = null;
        try {
            Produto produto = new Produto(null, "Parafuso", 12.3, Calendar.getInstance(), Calendar.getInstance(), 10);
            int addedId = dao.add(produto).getId();
            dao.commit();
            dao.deleteById(addedId);
            List<Produto> all = dao.getAll();
            Assertions.assertTrue(all.size() == 0);
        } catch (Exception e) {

        }
    }
}

