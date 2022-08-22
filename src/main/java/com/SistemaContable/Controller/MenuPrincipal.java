package com.SistemaContable.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class MenuPrincipal {
    @GetMapping({"/",""})
    public String verMenu(Model model){
        model.addAttribute("dato","Hola Mundo");
        return "index";
    }
    @GetMapping("/Catalogo")
    public String catalogo(Model model){
        model.addAttribute("dato","Hola Mundo");
        return "catalogo";
    }
    @GetMapping("/LibroDiario")
    public String LibroDiario(Model model){
        model.addAttribute("dato","Hola Mundo");
        return "LibroDiario";
    }
    @GetMapping("/LibroMayor")
    public String LibroMayor(Model model){
        model.addAttribute("dato","Hola Mundo");
        return "LibroMayor";
    }
    @GetMapping("/BalanceDeComprobacion")
    public String BalanceDeComprobacion(Model model){
        model.addAttribute("dato","Hola Mundo");
        return "BalanceDeComprobacion";
    }
    @GetMapping("/EstadoDeResultado")
    public String EstadoDeResultado(Model model){
        model.addAttribute("dato","Hola Mundo");
        return "EstadoDeResultado";
    }

    @GetMapping("/BalanceGeneral")
    public String BalanceGeneral(Model model){
        model.addAttribute("dato","Hola Mundo");
        return "BalanceGeneral";
    }

}
