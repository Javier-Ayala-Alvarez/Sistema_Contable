package com.SistemaContable.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MenuPrincipal {
    @GetMapping({"/",""})
    public String verMenu(Model model){
        model.addAttribute("dato","Hola Mundo");
        return "index";
    }
}
