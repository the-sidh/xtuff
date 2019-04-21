package br.com.equals.xtuff.domain.services.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.equals.xtuff.domain.entities.Comerciante;
import br.com.equals.xtuff.domain.entities.Endereco;
import br.com.equals.xtuff.domain.entities.Loja;
import br.com.equals.xtuff.domain.services.CadastroService;
import br.com.equals.xtuff.domain.services.ComercianteService;
import br.com.equals.xtuff.domain.services.EnderecoService;
import br.com.equals.xtuff.repositories.ComercianteRepository;
import br.com.equals.xtuff.repositories.EnderecoRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class CadastroServiceImplTest {

    @InjectMocks
    CadastroServiceImpl cadastroService;
    @Mock
    EnderecoService enderecoService;
    @Mock
    ComercianteService comercianteService;

    @Test
    @DisplayName("Deve cadastrar um comerciante com o devido endereco configurado")
    public void DeveCadastrarComercianteComEndereco() {
        Comerciante comerciante = new Comerciante("Luke", "Skywalker", "comerciante@equals.com", "123");
        Endereco endereco = new Endereco("Rua de terra", "Fazenda do tio Lars e da tia Beru", "s/n", "casinha 1", "Perto de Mos Isley", "Tatooine", "0123445");
        Loja loja = new Loja("Loja equals");
        when(comercianteService.addLoja(any(Comerciante.class), any(Loja.class))).thenReturn(loja);
        when(enderecoService.addEndereco(any(Endereco.class))).thenReturn(endereco);
        Loja persistedLoja = cadastroService.createLoja(comerciante, loja, endereco);
        Assertions.assertEquals(persistedLoja.getEndereco().getEstado(),"Tatooine");
    }

}
