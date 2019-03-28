package br.com.equals.xtuff.dao;

import java.util.List;

public interface GenericDao<K> {

    public List<K> getAll();
    public K add(K toAdd);
    public K getById(Integer id);
    public void deleteById(Integer id) throws Exception;
    public void update(K updated);
    public void deleteAll();

}
