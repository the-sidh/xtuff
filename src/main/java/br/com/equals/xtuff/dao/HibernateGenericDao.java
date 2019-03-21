package br.com.equals.xtuff.dao;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class HibernateGenericDao<K> implements GenericDao<K> {

    protected EntityManager em;

    private Class<K> clazz;

    @SuppressWarnings("all")
    public HibernateGenericDao(EntityManager em) {
        this.em = em;
        clazz = (Class<K>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public List<K> getAll() {
        return em.createQuery("from " + clazz.getName(), clazz).getResultList();
    }

    public void add(K toAdd) {
        em.persist(toAdd);
    }

    public K getById(Integer id) {
        return em.find(clazz, id);
    }

    public void deleteById(Integer id) throws Exception {
        K entity = getById(id);
        if (entity == null){
            throw new Exception("Not found");
        }
        em.remove(entity);
    }

    public void update(K updated) {
        em.merge(updated);
    }

    public void commit() throws Exception {
        try{
            em.getTransaction().begin();
            em.getTransaction().commit();
        }catch(Exception e){
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            e.printStackTrace();
            throw new Exception("Commit error");
        }
    }
}
