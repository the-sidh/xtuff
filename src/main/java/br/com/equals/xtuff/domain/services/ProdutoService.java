package br.com.equals.xtuff.domain.services;


import java.util.List;

import br.com.equals.xtuff.domain.entities.Produto;

public interface ProdutoService {

    public List<Produto> listProducts();
    public Produto showProduct(int id);
    public boolean deleteProduct(int id);
    public void updateProduct(Produto product);
    public Produto addProduct(Produto product);
}
