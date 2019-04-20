package br.com.equals.xtuff.domain.services;

import br.com.equals.xtuff.domain.entities.Loja;
import br.com.equals.xtuff.domain.entities.Comerciante;

public interface LojaService {

    public Loja addLoja(Loja loja);
    public void updateLoja(Loja loja);
}
