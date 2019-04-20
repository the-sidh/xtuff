package br.com.equals.xtuff.domain.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.equals.xtuff.domain.entities.Loja;
import br.com.equals.xtuff.domain.services.LojaService;
import br.com.equals.xtuff.repositories.LojaRepository;

@Service
public class LojaServiceImpl implements LojaService {

    @Autowired
    LojaRepository lojaDao;


    @Override
    public Loja addLoja(Loja loja) {
        Loja persistedLoja = lojaDao.save(loja);

        return persistedLoja;
    }

    @Override
    public void updateLoja(Loja loja) {
        lojaDao.save(loja);
    }


}
