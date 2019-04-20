package br.com.equals.xtuff.domain.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.equals.xtuff.domain.entities.Endereco;
import br.com.equals.xtuff.domain.services.EnderecoService;
import br.com.equals.xtuff.repositories.EnderecoRepository;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    @Autowired
    private EnderecoRepository enderecoDao;

    @Override
    public Endereco addEndereco(Endereco endereco) {
        Endereco persistedEndereco = enderecoDao.save(endereco);
        return persistedEndereco;
    }

    @Override
    public void updateEndereco(Endereco endereco) {
        enderecoDao.save(endereco);
    }
}
