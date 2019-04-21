package br.com.equals.xtuff.domain.services;

import br.com.equals.xtuff.domain.entities.Loja;
import br.com.equals.xtuff.domain.entities.Comerciante;
import br.com.equals.xtuff.domain.entities.Produto;

public interface LojaService {

    public void updateLoja(Loja loja);
    public void addProduto(Loja loja, Produto produto);
}
