package br.com.equals.xtuff.web.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import br.com.equals.xtuff.domain.entities.Comerciante;
import br.com.equals.xtuff.domain.entities.Loja;
import br.com.equals.xtuff.domain.entities.Produto;
import br.com.equals.xtuff.domain.exceptions.EntityNotFoundException;
import br.com.equals.xtuff.domain.services.LojaService;
import br.com.equals.xtuff.domain.services.ProdutoService;

@Controller
@RequestMapping("/api/")
public class ProdutoRestController {

    @Autowired
    ProdutoService produtoService;

    @Autowired
    LojaService lojaService;

    @GetMapping("/produtos")
    public ResponseEntity<Set<Produto>> listProdutos(Principal principal) {
        Loja loja = getLojaFromPrincipal(principal);
        if (loja == null) {
            return new ResponseEntity<Set<Produto>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Set<Produto>>(loja.getProdutos(), HttpStatus.OK);
    }

    @PostMapping("/add-produto")
    public ResponseEntity<Produto> addProduct(@RequestBody Produto produto, Principal principal) {
        Loja loja = getLojaFromPrincipal(principal);
        if (loja != null) {
            Produto persistedProduto = produtoService.addProduto(loja, produto);
            return new ResponseEntity<Produto>(persistedProduto, HttpStatus.OK);

        } else {
            return new ResponseEntity<Produto>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/edit-produto")
    public ResponseEntity<Produto> editProduct(@RequestBody Produto produto, BindingResult bindingResult, Principal principal, Model model) {
        Loja loja = getLojaFromPrincipal(principal);
        if(loja==null)
            return new ResponseEntity<Produto>(HttpStatus.NO_CONTENT);
        Produto originalProduto = null;
        try {
            originalProduto = produtoService.showProduct(produto.getId());
            if (originalProduto.getLoja() != loja) {
                return new ResponseEntity<Produto>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
            }
            produto.setLoja(loja);
            if (loja != null) {
                Produto persistedProduto = produtoService.updateProduct(produto);
                return new ResponseEntity<Produto>(persistedProduto, HttpStatus.OK);

            } else {
                return new ResponseEntity<Produto>(HttpStatus.NO_CONTENT);
            }
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<Produto>(HttpStatus.NO_CONTENT);
        }
    }



    @DeleteMapping("/delete-produto/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id, Principal principal) {
        Loja loja = getLojaFromPrincipal(principal);
        if(loja==null)
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        try {
            Produto produto = produtoService.showProduct(id);
            if (produto.getLoja() != loja) {
                return new ResponseEntity<Void>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
            }
            produtoService.deleteProduct(id);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/show-produto/{id}")
    public ResponseEntity<Produto> show(@PathVariable("id") Integer id, Principal principal) {
        Loja loja = getLojaFromPrincipal(principal);
        if(loja==null)
            return new ResponseEntity<Produto>(HttpStatus.NO_CONTENT);
        try {
            Produto produto = produtoService.showProduct(id);
            if (produto.getLoja() != loja) {
                return new ResponseEntity<Produto>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
            }
            return new ResponseEntity<Produto>(produto,HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<Produto>(HttpStatus.NO_CONTENT);
        }
    }

    private Loja getLojaFromPrincipal(Principal principal) {
        Loja loja = null;
        try {
            Comerciante comerciante = (Comerciante) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
            if (comerciante.getLoja() == null)
                return null;
            loja = lojaService.findLoja(comerciante.getLoja().getId());

        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return loja;
    }

}
