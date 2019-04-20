package br.com.equals.xtuff.domain.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Optional;

import br.com.equals.xtuff.domain.entities.Comerciante;
import br.com.equals.xtuff.domain.entities.Loja;
import br.com.equals.xtuff.domain.entities.Produto;
import br.com.equals.xtuff.domain.exceptions.EntityNotFoundException;
import br.com.equals.xtuff.domain.services.ComercianteService;
import br.com.equals.xtuff.domain.services.LojaService;
import br.com.equals.xtuff.domain.services.ProdutoService;
import br.com.equals.xtuff.repositories.ProdutoRepository;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private LojaService lojaService;

    @Autowired
    private ComercianteService comercianteService;


    @Override
    public List<Produto> listProducts() {
        return produtoRepository.findAll();
    }

    @Override
    public Produto showProduct(int id) throws EntityNotFoundException {
        Optional<Produto> produto = produtoRepository.findById(id);

        if (!produto.isPresent())
            throw new EntityNotFoundException("id-" + id);
        return produto.get();
    }

    @Override
    public boolean deleteProduct(int id) {
        boolean retorno = true;
        try {
            produtoRepository.deleteById(id);
        } catch (Exception e) {
            retorno = false;
        }
        return retorno;
    }

    @Override
    public void updateProduct(Produto product, Comerciante comerciante) {
        product.setLoja(comerciante.getLoja());
        try {
            produtoRepository.save(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Produto persistProduct(Produto product) {
        Produto persistedProduto = null;
        try {
            persistedProduto = produtoRepository.save(product);
        } catch (Exception e) {

        }
        return persistedProduto;
    }

    @Override
    public void AddProductToStore(@ModelAttribute Produto produto, Comerciante comerciante) {
        Loja loja = comerciante.getLoja();
        produto.setLoja(loja);
        Produto persistedProduto = persistProduct(produto);
        loja.getProdutos().add(persistedProduto);
        comercianteService.updateComerciante(comerciante);
    }
}
