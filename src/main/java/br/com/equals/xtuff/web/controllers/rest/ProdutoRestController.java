package br.com.equals.xtuff.web.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

import br.com.equals.xtuff.domain.entities.Comerciante;
import br.com.equals.xtuff.domain.entities.Produto;
import br.com.equals.xtuff.domain.exceptions.EntityNotFoundException;
import br.com.equals.xtuff.domain.services.ComercianteService;
import br.com.equals.xtuff.domain.services.LojaService;
import br.com.equals.xtuff.domain.services.ProdutoService;

@Controller
@RequestMapping("/api/")
public class ProdutoRestController {

    @Autowired
    ComercianteService comercianteService;

    @Autowired
    ProdutoService produtoService;

    @Autowired
    LojaService lojaService;

    @PostMapping("/add-produto")
    public String addProduct(@ModelAttribute Produto produto, BindingResult bindingResult, Principal principal, Model model) {
        String email = principal.getName();
        Comerciante comerciante = comercianteService.findByEmail(principal.getName());
        lojaService.addProduto(comerciante.getLoja(), produto);
        model.addAttribute("comerciante", comerciante);
        return "welcome";
    }

    @PostMapping("/edit-produto")
    public String editProduct(@ModelAttribute Produto produto, BindingResult bindingResult, Principal principal, Model model) {
        String email = principal.getName();
        Comerciante comerciante = comercianteService.findByEmail(principal.getName());
        produtoService.updateProduct(produto, email);
        return "";
    }


    @GetMapping("/produto-delete/{id}")
    public String delete(@PathVariable("id") Integer id, Principal principal, Model model) {
        Comerciante comerciante = comercianteService.findByEmail(principal.getName());
        produtoService.deleteProduct(id);
        model.addAttribute("comerciante", comerciante);
        return "welcome";
    }

}
