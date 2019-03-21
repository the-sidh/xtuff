import br.com.equals.xtuff.dao.LojaDao;
import br.com.equals.xtuff.domain.entities.Loja;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Test {


    public static void main(String[] args) {
        EntityManager em = null;
        try {
            em = Persistence.createEntityManagerFactory("xtuff").createEntityManager();
            LojaDao dao = new LojaDao(em);

            Loja loja = new Loja(100, "Teste", "sid@teste.com", "123", null);
            dao.add(loja);

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
