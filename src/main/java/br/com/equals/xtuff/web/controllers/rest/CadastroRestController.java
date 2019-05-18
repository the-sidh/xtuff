package br.com.equals.xtuff.web.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

import br.com.equals.xtuff.domain.entities.Comerciante;
import br.com.equals.xtuff.domain.entities.Endereco;
import br.com.equals.xtuff.domain.entities.Loja;
import br.com.equals.xtuff.domain.services.CadastroService;
import br.com.equals.xtuff.domain.services.ComercianteService;
import br.com.equals.xtuff.web.domain.entities.CadastroWrapper;

@Controller
@RequestMapping("/api")
public class CadastroRestController {

    @Autowired
    CadastroService cadastroService;

    @PostMapping("/add-loja")
    public ResponseEntity<Loja> addLoja(
            @RequestBody CadastroWrapper cadastro,
            Principal principal
    ) {
        Endereco endereco = new Endereco(cadastro.getLogradouro(), cadastro.getBairro(), cadastro.getNumero(), cadastro.getComplemento(), cadastro.getCidade(), cadastro.getEstado(), cadastro.getCep());
        Loja loja = new Loja(cadastro.getNome());

        Comerciante comerciante = (Comerciante) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        cadastroService.createLoja(comerciante, loja, endereco);
        return new ResponseEntity<Loja>(loja, HttpStatus.OK);
    }
}
