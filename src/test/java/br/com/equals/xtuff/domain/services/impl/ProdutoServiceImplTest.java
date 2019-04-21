package br.com.equals.xtuff.domain.services.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Calendar;
import java.util.Optional;

import br.com.equals.xtuff.domain.entities.Produto;
import br.com.equals.xtuff.domain.exceptions.EntityNotFoundException;
import br.com.equals.xtuff.repositories.ProdutoRepository;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class ProdutoServiceImplTest {

    @InjectMocks
    private ProdutoServiceImpl produtoService;


    @Mock
    private ProdutoRepository produtoRepository;

    @Test
    @DisplayName("Deve retornar um produto")
    public void deveRetornarProduto() {

        Produto produto = new Produto(1,"Parafuso", 1.0d, Calendar.getInstance(), Calendar.getInstance(), 1, null) ;
        Optional<Produto> opt = Optional.of(produto);

        Mockito.when(produtoRepository.findById(1))
                .thenReturn(opt);

        Produto p = null;
        try {
            p = produtoService.showProduct(1);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(p.getNome(),"Parafuso");

    }
}
