package br.com.equals.xtuff.domain.services;

import br.com.equals.xtuff.domain.entities.Comerciante;

public interface DashboardService {

    public boolean ComercianteHasLoja(Comerciante comerciante);
    public Comerciante getComerciante(String email);

}
