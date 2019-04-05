package br.com.equals.xtuff.domain.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.equals.xtuff.dao.ComercianteDao;
import br.com.equals.xtuff.domain.entities.Comerciante;
import br.com.equals.xtuff.domain.entities.Loja;
import br.com.equals.xtuff.domain.exceptions.UnauthorizedException;
import br.com.equals.xtuff.domain.services.ComercianteService;

@Service
public class ComercianteServiceImpl implements ComercianteService {

    @Autowired
    private ComercianteDao dao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(Comerciante comerciante) {
        comerciante.setSenha(bCryptPasswordEncoder.encode(comerciante.getSenha()));
        dao.add(comerciante);
        try {
            dao.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Comerciante findByUsername(String username) {
        return dao.findByUsername(username);
    }

    @Override
    public void updateComerciante(Comerciante comerciante) {
        dao.update(comerciante);
        try {
            dao.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addLoja(Comerciante comerciante, Loja loja) {

    }

    @Override
    public Loja getLoja(Comerciante comerciante) {
        return comerciante.getLoja();
    }
}
