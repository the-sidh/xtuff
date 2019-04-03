package br.com.equals.xtuff.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProdutoController {

    @GetMapping("/produtos")
    public String listAllProducts() {
        return "hw";
    }
}
