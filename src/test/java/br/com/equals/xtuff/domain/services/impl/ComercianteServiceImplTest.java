package br.com.equals.xtuff.domain.services.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import br.com.equals.xtuff.domain.entities.Comerciante;
import br.com.equals.xtuff.domain.entities.Loja;
import br.com.equals.xtuff.repositories.ComercianteRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class ComercianteServiceImplTest {

    @InjectMocks
    private ComercianteServiceImpl comercianteService;

    @Mock
    private ComercianteRepository comercianteRepository;

    @Test
    @DisplayName("Dado um email, deve retornar o comerciante associado")
    public void dadoEmailRetornaComerciante() {
        Comerciante comerciante = new Comerciante("Luke", "Skywalker","comerciante@equals.com","123");
        ArrayList<Comerciante> list = new ArrayList<Comerciante>();
        list.add(comerciante);
        Optional<Comerciante> opt = Optional.of(comerciante);

        when(comercianteRepository.findByEmail("comerciante@equals.com"))
                .thenReturn(list);

        String email = "comerciante@equals.com";
        Comerciante comercianteRecuperado = comercianteService.findByEmail(email);
        Assertions.assertEquals(comercianteRecuperado.getNome(),"Luke");
    }

    @Test
    @DisplayName("Deve adicionar uma nova loja")
    public void DeveAdicionarLoja(){
        Loja loja = new Loja("Loja equals");
        Comerciante comerciante = new Comerciante("Luke", "Skywalker","comerciante@equals.com","123");

        when(comercianteRepository.save(any(Comerciante.class))).thenReturn(comerciante);
        Loja lojaPersistida = comercianteService.addLoja(comerciante,loja);
        Assertions.assertEquals(lojaPersistida.getNome(),"Loja equals");

    }

}