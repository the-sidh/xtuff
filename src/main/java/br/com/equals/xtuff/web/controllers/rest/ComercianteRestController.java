package br.com.equals.xtuff.web.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import br.com.equals.xtuff.domain.entities.Comerciante;
import br.com.equals.xtuff.domain.entities.Produto;
import br.com.equals.xtuff.domain.services.ComercianteService;

@RestController
@RequestMapping("/api/")
public class ComercianteRestController {

    @Autowired
    private ComercianteService comercianteService;

        @PostMapping("/registration")
        public void signUp(@RequestBody Comerciante comerciante) {
            comercianteService.save(comerciante);
        }

    @GetMapping("/produtos")
    public Set<Produto> listProdutos(HttpServletRequest request) {
        Comerciante comerciante = (Comerciante) request.getAttribute("comerciante");
        return (Set<Produto>) comercianteService.getProdutos(comerciante.getEmail());
    }
    }

