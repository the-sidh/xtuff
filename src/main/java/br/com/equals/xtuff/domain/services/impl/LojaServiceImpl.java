package br.com.equals.xtuff.domain.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.equals.xtuff.domain.entities.Loja;
import br.com.equals.xtuff.domain.entities.Produto;
import br.com.equals.xtuff.domain.services.LojaService;
import br.com.equals.xtuff.repositories.LojaRepository;

@Service
public class LojaServiceImpl implements LojaService {

    @Autowired
    LojaRepository lojaDao;


    @Override
    public void updateLoja(Loja loja) {
        lojaDao.save(loja);
    }

    @Override
    public void addProduto(Loja loja, Produto produto) {

    }


}
