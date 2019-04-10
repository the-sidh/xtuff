package br.com.equals.xtuff.domain.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.equals.xtuff.dao.LojaDao;
import br.com.equals.xtuff.domain.entities.Comerciante;
import br.com.equals.xtuff.domain.entities.Loja;
import br.com.equals.xtuff.domain.services.LojaService;

@Service
public class LojaServiceImpl implements LojaService {

    @Autowired
    LojaDao lojaDao;


    @Override
    public Loja addLoja(Loja loja) {
        Loja persistedLoja = lojaDao.add(loja);
        try {
            lojaDao.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return persistedLoja;
    }

    @Override
    public void updateLoja(Loja loja) {
        lojaDao.update(loja);
        try {
            lojaDao.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void commit() {
        try {
            lojaDao.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
