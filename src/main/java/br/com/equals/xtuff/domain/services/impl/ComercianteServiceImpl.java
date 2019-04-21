package br.com.equals.xtuff.domain.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.equals.xtuff.domain.entities.Comerciante;
import br.com.equals.xtuff.domain.entities.Loja;
import br.com.equals.xtuff.domain.services.ComercianteService;
import br.com.equals.xtuff.repositories.ComercianteRepository;

@Service
public class ComercianteServiceImpl implements ComercianteService {

    @Autowired
    private ComercianteRepository comercianteRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(Comerciante comerciante) {
        comerciante.setSenha(bCryptPasswordEncoder.encode(comerciante.getSenha()));
        comercianteRepository.save(comerciante);
    }

    @Override
    public Comerciante findByEmail(String email) {
        return comercianteRepository.findByEmail(email).get(0);
    }

    @Override
    public void updateComerciante(Comerciante comerciante) {
        comercianteRepository.save(comerciante);
    }

    @Override
    public Loja addLoja(Comerciante comerciante, Loja loja) {
        comerciante.setLoja(loja);
        return comercianteRepository.save(comerciante).getLoja();
    }

    @Override
    public Loja getLoja(Comerciante comerciante) {
        return comerciante.getLoja();
    }
}
