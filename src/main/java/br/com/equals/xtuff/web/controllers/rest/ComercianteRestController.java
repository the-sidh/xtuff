package br.com.equals.xtuff.web.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.equals.xtuff.domain.entities.Comerciante;
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
    }

