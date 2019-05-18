package br.com.equals.xtuff.web.controllers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import br.com.equals.xtuff.auth.spring.UserValidator;
import br.com.equals.xtuff.domain.entities.Comerciante;
import br.com.equals.xtuff.domain.services.ComercianteService;
import br.com.equals.xtuff.domain.services.spring.SecurityService;

@Controller
@RequestMapping("/web")
public class ComercianteController {
    @Autowired
    private ComercianteService comercianteService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("comerciante", new Comerciante());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@Valid Comerciante comerciante, BindingResult bindingResult) {
        userValidator.validate(comerciante, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        comercianteService.save(comerciante);

        securityService.autoLogin(comerciante.getEmail(), comerciante.getPasswordConfirm());

        return "redirect:/web/welcome";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Usuário e/ou senha inválidos.");

        if (logout != null)
            model.addAttribute("message", "Você foi deslogado com sucesso.");

        return "login";
    }
}
