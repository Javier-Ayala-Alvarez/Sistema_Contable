//Está clase se dedica a redirecionar las diferentes paginas segun el boton  que se seleccione o precione


package com.SistemaContable.Controller;

import com.SistemaContable.servicio.JavierAyala.Interfaces.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller

public class RegistroController {

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
        modelo.addAttribute("usuario", "usuarioServicio");
        return "index";
    }

    //@GetMapping va a direccionar al catálogo cuando la url defina eso despues de /


    /*@GetMapping("/LibroDiario")
    public String LibroDiario(Model model) {
        model.addAttribute("tituloDeLaPagina", "Libro Diario: Diego Mejia");
        return "LibroDiario";
<<<<<<< HEAD
    }*/

    @GetMapping("/Configuracion")
    public String Configuracion(Model model) {
        model.addAttribute("tituloDeLaPagina", "Configuración: Gerson");
        return "configuracion";
    }

    @GetMapping("/LibroMayor")
    public String LibroMayor(Model model) {
        model.addAttribute("tituloDeLaPagina", "Libro Mayor: Deras");
        return "LibroMayor";
    }
    @GetMapping("/Cierre")
    public String cierre(Model model) {
        model.addAttribute("tituloDeLaPagina", "Cierre contable: Deras");
        return "cierre";
    }

    @GetMapping("/BalanceDeComprobacion")
    public String BalanceDeComprobacion(Model model) {
        model.addAttribute("tituloDeLaPagina", "Balance de comprobación: Gerson");
        return "BalanceDeComprobacion";
    }



    @GetMapping("/BalanceGeneral")
    public String BalanceGeneral(Model model) {
        model.addAttribute("tituloDeLaPagina", "Balance General: Diego");
        return "BalanceGeneral";
    }


}
