package br.com.equals.xtuff.domain.services;

import java.util.HashSet;
import java.util.List;

import br.com.equals.xtuff.domain.entities.Loja;
import br.com.equals.xtuff.domain.entities.Comerciante;
import br.com.equals.xtuff.domain.entities.Produto;
import br.com.equals.xtuff.domain.exceptions.EntityNotFoundException;

public interface LojaService {

    public void updateLoja(Loja loja);
    Loja addLoja(Loja loja);

    Loja findLoja(int id) throws EntityNotFoundException;

    Loja persistLoja(Loja loja);
}
