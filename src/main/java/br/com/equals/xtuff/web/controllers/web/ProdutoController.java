package br.com.equals.xtuff.web.controllers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

import br.com.equals.xtuff.domain.entities.Comerciante;
import br.com.equals.xtuff.domain.entities.Loja;
import br.com.equals.xtuff.domain.entities.Produto;
import br.com.equals.xtuff.domain.exceptions.EntityNotFoundException;
import br.com.equals.xtuff.domain.services.ComercianteService;
import br.com.equals.xtuff.domain.services.LojaService;
import br.com.equals.xtuff.domain.services.ProdutoService;

@Controller
@RequestMapping("/web")
public class ProdutoController {

    @Autowired
    ComercianteService comercianteService;

    @Autowired
    ProdutoService produtoService;

    @Autowired
    LojaService lojaService;

    @GetMapping("/add-produto")
    public String registration(Model model) {
        model.addAttribute("produto", new Produto());
        return "add-produto";
    }

    @PostMapping("/add-produto")
    public String addProduct(@ModelAttribute Produto produto, BindingResult bindingResult, Principal principal, Model model ) {
        String email = principal.getName();
        Comerciante comerciante = comercianteService.findByEmail(principal.getName());
        produtoService.addProduto(comerciante.getLoja(), produto);
        model.addAttribute("comerciante", comerciante);
        return "welcome";
    }

    @PostMapping("/edit-produto")
    public String editProduct(@ModelAttribute Produto produto, BindingResult bindingResult, Principal principal, Model model ) {
        Loja loja = getLojaFromPrincipal(principal);
        Comerciante comerciante = comercianteService.findByEmail(principal.getName());
        produto.setLoja(loja);
        produtoService.updateProduct(produto);
        model.addAttribute("comerciante", comerciante);
        return "welcome";
    }

    @GetMapping("/edit-produto/{id}")
    public String editProduct(@PathVariable("id") Integer id, Model model ) {
        try {
            Produto produto = produtoService.showProduct(id);
            model.addAttribute("produto", produto);
        }catch (EntityNotFoundException e){

        }

        return "edit-produto";
    }

    @GetMapping("/produto-delete/{id}")
    public String delete(@PathVariable("id") Integer id, Principal principal, Model model) {
        Comerciante comerciante = comercianteService.findByEmail(principal.getName());
        produtoService.deleteProduct(id);
        model.addAttribute("comerciante", comerciante);
        return "welcome";
    }

    private Loja getLojaFromPrincipal(Principal principal) {
        Loja loja = null;
        try {
            Comerciante comerciante = comercianteService.findByEmail(principal.getName());
            loja = lojaService.findLoja(comerciante.getLoja().getId());

        } catch (EntityNotFoundException e) {
            e.printStackTrace();
        }
        return loja;
    }


}
