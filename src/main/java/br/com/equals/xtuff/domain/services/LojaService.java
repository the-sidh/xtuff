package br.com.equals.xtuff.domain.services;

import java.util.List;

import br.com.equals.xtuff.domain.entities.Produto;
import br.com.equals.xtuff.domain.entities.User;

public interface LojaService {

    public List<Produto> getProdutos();
    public User getUser();
}
