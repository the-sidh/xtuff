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

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;

import br.com.equals.xtuff.domain.entities.Comerciante;
import br.com.equals.xtuff.domain.entities.Loja;
import br.com.equals.xtuff.domain.entities.Produto;
import br.com.equals.xtuff.domain.services.LojaService;
import br.com.equals.xtuff.domain.services.ProdutoService;
import br.com.equals.xtuff.repositories.LojaRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class LojaServiceImplTest {
    @InjectMocks
    LojaServiceImpl lojaService;

    @Mock
    LojaRepository lojaRepository;

    @Mock
    ProdutoService produtoService;

    @DisplayName("Deve adicionar um produto na loja")
    @Test
    public void DeveAdicionarUmProduto(){
        Loja loja = new Loja("Loja equals");
        Produto produto = new Produto(1,"Ligth Saber", 1.0d, Calendar.getInstance(), Calendar.getInstance(), 1, null) ;
        when(produtoService.persistProduct(any(Produto.class))).thenReturn(produto);
        when(lojaRepository.save(any(Loja.class))).thenReturn(loja);
        HashSet<Produto> produtos = lojaService.addProduto(loja,produto);
        Assertions.assertTrue(produtos.contains(produto));
    }

}
