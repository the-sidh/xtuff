package br.com.equals.xtuff.domain.services;

import br.com.equals.xtuff.domain.entities.Comerciante;
import br.com.equals.xtuff.domain.entities.Loja;
import br.com.equals.xtuff.domain.exceptions.UnauthorizedException;

public interface ComercianteService {
    void save(Comerciante comerciante);
    Comerciante findByEmail(String username);
    public void updateComerciante(Comerciante comerciante);
    public Loja addLoja(Comerciante comerciante , Loja loja);
    public Loja getLoja(Comerciante comerciante);
}
