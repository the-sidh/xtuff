package br.com.equals.xtuff.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.equals.xtuff.domain.entities.Comerciante;
import br.com.equals.xtuff.domain.entities.Endereco;
import br.com.equals.xtuff.domain.entities.Loja;

@Service
public class CadastroService {

    @Autowired
    EnderecoService enderecoService;

    @Autowired
    LojaService lojaService;

    @Autowired
    ComercianteService comercianteService;

    public void createLoja(Comerciante comerciante, Loja loja, Endereco endereco){

        Endereco persistedEndereco = enderecoService.addEndereco(endereco);
        loja.setEndereco(persistedEndereco);
        Loja persistedLoja = lojaService.addLoja(loja);
        comerciante.setLoja(persistedLoja);
        comercianteService.updateComerciante(comerciante);
    }
}
