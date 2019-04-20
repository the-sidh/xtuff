package br.com.equals.xtuff.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

import br.com.equals.xtuff.domain.entities.Comerciante;
import br.com.equals.xtuff.domain.entities.Endereco;
import br.com.equals.xtuff.domain.entities.Loja;
import br.com.equals.xtuff.domain.services.CadastroService;
import br.com.equals.xtuff.domain.services.ComercianteService;

@Controller
public class CadastroController {

    @Autowired
    CadastroService cadastroService;

    @Autowired
    ComercianteService comercianteService;
    private Endereco endereco;

    @PostMapping("/add-loja")
    public String login(
            @RequestParam("nome") String nome,
            @RequestParam("logradouro") String logradouro,
            @RequestParam("bairro") String bairro,
            @RequestParam("numero") String numero,
            @RequestParam("complemento") String complemento,
            @RequestParam("cidade") String cidade,
            @RequestParam("estado") String estado,
            @RequestParam("cep") String cep,
            Principal principal
    ) {
        Endereco endereco = new Endereco(logradouro, bairro, numero, complemento, cidade, estado, cep);
        Loja loja = new Loja(nome);
        Comerciante comerciante = comercianteService.findByEmail(principal.getName());
        cadastroService.createLoja(comerciante, loja, endereco);
        return "welcome";
    }
}
