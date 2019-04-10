package br.com.equals.xtuff.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.equals.xtuff.domain.entities.Comerciante;
import br.com.equals.xtuff.domain.entities.Endereco;
import br.com.equals.xtuff.domain.entities.Loja;

@Service
public interface CadastroService {
    public void createLoja(Comerciante comerciante, Loja loja, Endereco endereco);
}
