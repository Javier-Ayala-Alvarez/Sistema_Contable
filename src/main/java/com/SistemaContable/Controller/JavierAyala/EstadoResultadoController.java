package com.SistemaContable.Controller.JavierAyala;

import com.SistemaContable.model.JavierAyala.RegistrosEstadosResultadoDAO;
import com.SistemaContable.servicio.JavierAyala.ServicesImplements.EstadoResultadoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.swing.*;

@Controller
public class EstadoResultadoController {

    @Autowired
    EstadoResultadoImpl estadoResultado;
    @GetMapping("/EstadoDeResultado")
    public String EstadoDeResultado(Model model) {
        model.addAttribute("tituloDeLaPagina", "Estado de Resultado");
        model.addAttribute("estados", estadoResultado.mostrar());
        return "EstadoDeResultado";
    }
}
