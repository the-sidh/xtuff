package br.com.equals.xtuff.dao;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;


import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.equals.xtuff.domain.entities.Comerciante;
import br.com.equals.xtuff.domain.entities.Endereco;
import br.com.equals.xtuff.domain.entities.Loja;
import br.com.equals.xtuff.domain.entities.Produto;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ComercianteDaoTest {

    private static EntityManager em = null;
    private static ComercianteDao dao;

    @BeforeAll
    public static void setup() {
        em = Persistence.createEntityManagerFactory("xtufftest").createEntityManager();
        dao = new ComercianteDao(em);
    }

    @AfterAll
    public static void shutDown() {
        em.close();
    }

    @BeforeEach
    public void wipeOut() {
        dao.deleteAll();
    }

    @Test
    public void um_comerciante_deve_ser_adicionado() {
        Comerciante comerciante = new Comerciante("Sid","Rezende","sid@equals.com.br","123");
        int idComerciante= dao.add(comerciante).getId();
        try {
            dao.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Comerciante insertedComerciante = dao.getById(idComerciante);
        Assertions.assertTrue(insertedComerciante.getNome().equals("Sid"));
    }


    @Test
    public void um_comerciante_deve_ser_encontrado_pelo_email() {
        Comerciante comerciante = new Comerciante("Sid","Rezende","sid@equals.com.br","123");
        int idComerciante= dao.add(comerciante).getId();
        try {
            dao.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Comerciante insertedComerciante = dao.findByUsername("sid@equals.com.br");


        Assertions.assertTrue(insertedComerciante.getNome().equals("Sid"));
    }

    @Test
    public void multiplos_comerciantes_devem_ser_adicionados() {

        try {
            for (int i = 0; i < 100; i++) {
                Comerciante comerciante = new Comerciante("Sid","Rezende","sid@equals.com.br","123");
                int addedId = dao.add(comerciante).getId();
                dao.commit();
            }
            List<Comerciante> all = dao.getAll();
            Assertions.assertTrue(all.size() == 100);

        } catch (Exception e) {

        }
    }

    @Test
    public void dado_um_id_uma_loja_deve_ser_removida() {
        try {
            Comerciante comerciante = new Comerciante("Sid","Rezende","sid@equals.com.br","123");
            int idComerciante= dao.add(comerciante).getId();
            dao.commit();
            dao.deleteById(idComerciante);
            dao.commit();
            List<Comerciante> all = dao.getAll();
            Assertions.assertTrue(all.size()==0);

        } catch (Exception e) {

        }
    }

    @Test
    public void uma_loja_deve_ser_criada_para_um_comerciante() {

        EnderecoDao endDao = new EnderecoDao(em);
        Endereco endereco =  endDao.add(new Endereco("Avenida Dom Pedro I, 500" , "Ipiranga","300","cj30","Sao Paulo", "SP", "01549-000"));
        try {
            endDao.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        LojaDao lojaDao = new LojaDao(em);
        Loja loja = new Loja("Teste") ;
        loja.setEndereco(endereco);
        Loja persistedLoja = lojaDao.add(loja);
        try {
            lojaDao.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Comerciante comerciante = new Comerciante("Sid","Rezende","sid@equals.com.br","123");
        comerciante.setLoja(persistedLoja);
        final Comerciante persistedComerciante = dao.add(comerciante);
        try {
            dao.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assertions.assertAll(
               new Executable() {
                   public void execute() throws Throwable {
                       Assertions.assertEquals(persistedComerciante.getNome() , "Sid");
                       Assertions.assertEquals(persistedComerciante.getLoja().getNome(), "Teste");
                   }
               }
        );
    }

    @Test
    public void uma_loja_deve_ser_criada_para_um_comerciante_e_produtos_adicionados() {

        ProdutoDao produtoDao = new ProdutoDao(em);
        Produto produto = produtoDao.add( new Produto(null, "Parafuso", 12.3, Calendar.getInstance(), Calendar.getInstance(), 10));
        try {
        produtoDao.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        EnderecoDao endDao = new EnderecoDao(em);
        Endereco endereco =  endDao.add(new Endereco("Avenida Dom Pedro I, 500" , "Ipiranga","300","cj30","Sao Paulo", "SP", "01549-000"));
        try {
            endDao.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        LojaDao lojaDao = new LojaDao(em);
        Loja loja = new Loja("Teste") ;
        loja.getProdutos().add(produto);
        loja.setEndereco(endereco);
        Loja persistedLoja = lojaDao.add(loja);
        try {
            lojaDao.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }


        Comerciante comerciante = new Comerciante("Sid","Rezende","sid@equals.com.br","123");
        comerciante.setLoja(persistedLoja);
        final Comerciante persistedComerciante = dao.add(comerciante);
        try {
            dao.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assertions.assertAll(
                new Executable() {
                    public void execute() throws Throwable {
                        Assertions.assertEquals(persistedComerciante.getNome() , "Sid");
                        Assertions.assertEquals(persistedComerciante.getLoja().getNome(), "Teste");
                        Assertions.assertEquals (persistedComerciante.getLoja().getProdutos().size(), 1);
                    }
                }
        );



    }
}
