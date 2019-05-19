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
import java.util.HashSet;
import java.util.Optional;

import br.com.equals.xtuff.domain.entities.Loja;
import br.com.equals.xtuff.domain.entities.Produto;
import br.com.equals.xtuff.domain.exceptions.EntityNotFoundException;
import br.com.equals.xtuff.repositories.ProdutoRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class ProdutoServiceImplTest {
//
//    @InjectMocks
//    private ProdutoServiceImpl produtoService;
//
//    @Mock
//    private LojaServiceImpl lojaService;
//
//
//    @Mock
//    private ProdutoRepository produtoRepository;
//
//    @Test
//    @DisplayName("Deve retornar um produto")
//    public void deveRetornarProduto() {
//
//        Produto produto = new Produto(1, "Ligth Saber", 1.0d, Calendar.getInstance(), Calendar.getInstance(), 1, null);
//        Optional<Produto> opt = Optional.of(produto);
//
//        Mockito.when(produtoRepository.findById(1))
//                .thenReturn(opt);
//
//        Produto p = null;
//        try {
//            p = produtoService.showProduct(1);
//        } catch (EntityNotFoundException e) {
//            e.printStackTrace();
//        }
//        Assertions.assertEquals(p.getNome(), "Ligth Saber");
//
//    }
//
//
//    @Test
//    @DisplayName("Deve remover um produto")
//    public void deveRemoverProduto() {
//        Loja loja = new Loja("Loja equals");
//        Produto produto = new Produto(1, "Ligth Saber", 1.0d, Calendar.getInstance(), Calendar.getInstance(), 1, null);
//        Optional<Produto> opt = Optional.of(produto);
//        HashSet<Produto> produtos = new HashSet<Produto>();
//        Mockito.when(produtoService.addProduto(any(Loja.class), any(Produto.class))).thenReturn(produto);
//
//        produtoService.addProduto(loja, produto);
//        produtoService.deleteProduct(1);
//        verify(produtoRepository, times(1)).deleteById(1);
//
//    }
//
//    @Test
//    @DisplayName("Deve editar um produto")
//    public void deveEditarProduto() {
//        Loja loja = new Loja("Loja equals");
//        Produto produto = new Produto(1, "Ligth Saber", 1.0d, Calendar.getInstance(), Calendar.getInstance(), 1, null);
//        Optional<Produto> opt = Optional.of(produto);
//        HashSet<Produto> produtos = new HashSet<Produto>();
//       // Mockito.when(produtoService.addProduto(any(Loja.class), any(Produto.class))).thenReturn(produto);
//       // Mockito.when(produtoRepository.findById(1)).thenReturn(opt);
//        produtoService.addProduto(loja, produto);
//
//        Produto p = null;
//        Produto p2 = null;
//        try {
//            p = produtoService.showProduct(1);
//            p.setPreco(2.0d);
//            produtoService.updateProduct(p);
//            p2 = produtoService.showProduct(1);
//        } catch (EntityNotFoundException e) {
//            e.printStackTrace();
//        }
//        produtoService.deleteProduct(1);
//        Assertions.assertEquals(p2.getPreco(), new Double(2.0d));
//    }
}
