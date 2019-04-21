package br.com.equals.xtuff.domain.services;

import java.util.HashSet;
import java.util.List;

import br.com.equals.xtuff.domain.entities.Loja;
import br.com.equals.xtuff.domain.entities.Comerciante;
import br.com.equals.xtuff.domain.entities.Produto;

public interface LojaService {

    public void updateLoja(Loja loja);
    public HashSet<Produto> addProduto(Loja loja, Produto produto);
}
