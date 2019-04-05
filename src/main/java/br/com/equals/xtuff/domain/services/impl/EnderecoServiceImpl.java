package br.com.equals.xtuff.domain.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.equals.xtuff.dao.EnderecoDao;
import br.com.equals.xtuff.domain.entities.Endereco;
import br.com.equals.xtuff.domain.services.EnderecoService;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    @Autowired
    private EnderecoDao enderecoDao;

    @Override
    public Endereco addEndereco(Endereco endereco) {
        Endereco persistedEndereco = enderecoDao.add(endereco);
        try {
            enderecoDao.commit();
        } catch (Exception e) {

        }
        return persistedEndereco;
    }

    @Override
    public void updateEndereco(Endereco endereco) {
        enderecoDao.update(endereco);
        try {
            enderecoDao.commit();
        } catch (Exception e) {

        }
    }
}
