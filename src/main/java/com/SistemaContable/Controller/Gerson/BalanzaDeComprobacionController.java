package com.SistemaContable.Controller.Gerson;

import com.SistemaContable.servicio.Gerson.BalanzaDeComprobacionInterfaz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class BalanzaDeComprobacionController {

    @Autowired
    BalanzaDeComprobacionInterfaz servicio;

    @GetMapping("/BalanzaDeComprobacion")
    public String listarBDC(Model modelo){
        modelo.addAttribute("BalanzaDeComprobacion",servicio.findAll());
        return "BalanceDeComprobacion";
    }

}
