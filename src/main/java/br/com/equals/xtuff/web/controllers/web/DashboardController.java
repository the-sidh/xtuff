package br.com.equals.xtuff.web.controllers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

import br.com.equals.xtuff.domain.entities.Comerciante;
import br.com.equals.xtuff.domain.services.ComercianteService;

@Controller
@RequestMapping("/web")
public class DashboardController {

    @Autowired
    private ComercianteService service;

    @RequestMapping({"/", "/welcome"})
    public String welcome(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        String email = user.getUsername();
        Comerciante comerciante = service.findByEmail(email);

        if(comerciante.getLoja()==null){
            return "cadastra-loja";
        }

        model.addAttribute("comerciante",comerciante);

        return "welcome";
    }
}
