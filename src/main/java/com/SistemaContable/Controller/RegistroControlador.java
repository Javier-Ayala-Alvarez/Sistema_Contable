//Está clase se dedica a redirecionar las diferentes paginas segun el boton  que se seleccione o precione


package com.SistemaContable.Controller;

import com.SistemaContable.Controller.dto.UsuarioRegistroDTO;
import com.SistemaContable.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistroControlador {

    //@Autowired es una dependencia para inyectar una clase en otra
    @Autowired
    private UsuarioServicio servicio;


    //@GetMappin permite definir la url que ejecutando hara el proceso que le indique el metodo,
    // en este caso direccionara a login.
    @GetMapping("/login")
    public String iniciarSesion() {
        return "login";
    }

    //@GetMapping va a enviar datos a la página principal
    @GetMapping("/")
    public String verPaginaDeInicio(Model modelo) {
        modelo.addAttribute("usuarios","Usuario");
        return "index";
    }
    //@GetMapping va a direccionar al catálogo cuando la url defina eso despues de /
    @GetMapping("/Catalogo")
    public String catalogo(Model model) {
        model.addAttribute("tituloDeLaPagina", "Catálogo");
        return "catalogo";
    }

    @GetMapping("/LibroDiario")
    public String LibroDiario(Model model) {
        model.addAttribute("tituloDeLaPagina", "Libro Diario");
        return "LibroDiario";
    }

    @GetMapping("/LibroMayor")
    public String LibroMayor(Model model) {
        model.addAttribute("tituloDeLaPagina", "Libro Mayor");
        return "LibroMayor";
    }

    @GetMapping("/BalanceDeComprobacion")
    public String BalanceDeComprobacion(Model model) {
        model.addAttribute("tituloDeLaPagina", "Balance de comprobación");
        return "BalanceDeComprobacion";
    }

    @GetMapping("/EstadoDeResultado")
    public String EstadoDeResultado(Model model) {
        model.addAttribute("tituloDeLaPagina", "Estado de Resultado");
        return "EstadoDeResultado";
    }

    @GetMapping("/BalanceGeneral")
    public String BalanceGeneral(Model model) {
        model.addAttribute("tituloDeLaPagina", "Balance General");
        return "BalanceGeneral";
    }


}