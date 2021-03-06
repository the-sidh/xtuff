package br.com.equals.xtuff.domain.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import br.com.equals.xtuff.domain.entities.Comerciante;
import br.com.equals.xtuff.domain.entities.Loja;
import br.com.equals.xtuff.domain.entities.Produto;
import br.com.equals.xtuff.domain.services.ComercianteService;
import br.com.equals.xtuff.repositories.ComercianteRepository;

@Service
@Transactional
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
        Comerciante comerciante = comercianteRepository.findByEmail(email).size() >0  ? comercianteRepository.findByEmail(email).get(0) : null;
        return comerciante;
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
    public Collection<Produto> getProdutos(String email) {
        Loja loja = findByEmail(email).getLoja();
        return loja.getProdutos();
    }
}
