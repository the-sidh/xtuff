package br.com.equals.xtuff.domain.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

import br.com.equals.xtuff.domain.entities.Loja;
import br.com.equals.xtuff.domain.entities.Produto;
import br.com.equals.xtuff.domain.services.LojaService;
import br.com.equals.xtuff.domain.services.ProdutoService;
import br.com.equals.xtuff.repositories.LojaRepository;

@Service
public class LojaServiceImpl implements LojaService {

    @Autowired
    LojaRepository lojaRepository;

    @Autowired
    ProdutoService produtoService;

    @Override
    public void updateLoja(Loja loja) {
        lojaRepository.save(loja);
    }

    @Override
    public HashSet<Produto> addProduto(Loja loja, Produto produto) {
        Produto persistedProduto = produtoService.persistProduct(produto);
        loja.getProdutos().add(persistedProduto);
        return (HashSet<Produto>) lojaRepository.save(loja).getProdutos();
    }

}
