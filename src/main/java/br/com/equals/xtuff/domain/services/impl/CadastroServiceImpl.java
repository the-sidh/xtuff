package br.com.equals.xtuff.domain.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.equals.xtuff.domain.entities.Comerciante;
import br.com.equals.xtuff.domain.entities.Endereco;
import br.com.equals.xtuff.domain.entities.Loja;
import br.com.equals.xtuff.domain.services.CadastroService;
import br.com.equals.xtuff.domain.services.ComercianteService;
import br.com.equals.xtuff.domain.services.EnderecoService;
import br.com.equals.xtuff.domain.services.LojaService;

@Service
public class CadastroServiceImpl implements CadastroService {

    @Autowired
    EnderecoService enderecoService;


    @Autowired
    ComercianteService comercianteService;

    @Override
    public void createLoja(Comerciante comerciante, Loja loja, Endereco endereco){
        Endereco persistedEndereco = enderecoService.addEndereco(endereco);
        loja.setEndereco(persistedEndereco);
        comercianteService.addLoja(comerciante, loja);
    }
}
