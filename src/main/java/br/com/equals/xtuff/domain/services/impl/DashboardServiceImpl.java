package br.com.equals.xtuff.domain.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.equals.xtuff.domain.entities.Comerciante;
import br.com.equals.xtuff.domain.services.ComercianteService;
import br.com.equals.xtuff.domain.services.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private ComercianteService comercianteService;

    @Override
    public boolean ComercianteHasLoja(Comerciante comerciante) {
        return comercianteService.getLoja(comerciante)!=null;
    }

    @Override
    public Comerciante getComerciante(String email) {
        return comercianteService.findByEmail(email);
    }
}
