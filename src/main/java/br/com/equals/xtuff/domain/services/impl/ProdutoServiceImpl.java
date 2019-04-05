package br.com.equals.xtuff.domain.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import br.com.equals.xtuff.dao.LojaDao;
import br.com.equals.xtuff.dao.ProdutoDao;
import br.com.equals.xtuff.domain.entities.Produto;
import br.com.equals.xtuff.domain.services.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoDao produtoDao;

    @Autowired
    private LojaDao lojaDao;

    @Override
    public List<Produto> listProducts() {
        return produtoDao.getAll();
    }

    @Override
    public Produto showProduct(int id) {
        return produtoDao.getById(id);
    }

    @Override
    public boolean deleteProduct(int id) {
        boolean retorno = true;
        try{
            produtoDao.deleteById(id);
        }catch (Exception e){
            retorno = false;
        }
        return retorno;
    }

    @Override
    public void updateProduct(Produto product) {
        produtoDao.update(product);
    }

    @Override
    public Produto addProduct(Produto product) {
        return produtoDao.add(product);
    }
}
