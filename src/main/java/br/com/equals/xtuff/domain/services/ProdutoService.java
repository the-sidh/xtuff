package br.com.equals.xtuff.domain.services;


import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

import br.com.equals.xtuff.domain.entities.Comerciante;
import br.com.equals.xtuff.domain.entities.Loja;
import br.com.equals.xtuff.domain.entities.Produto;
import br.com.equals.xtuff.domain.exceptions.EntityNotFoundException;

public interface ProdutoService {

    public Produto showProduct(int id) throws EntityNotFoundException;
    public boolean deleteProduct(int id);

    Produto updateProduct(Produto product);

    Produto persistProduct(Produto product);

    Produto addProduto(Loja loja, Produto produto);
}
