package com.SistemaContable.Controller.DiegoMejia;

import com.SistemaContable.servicio.DiegoMejia.Interfaces.PartidaServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PartidaController {
    @Autowired
    private PartidaServiceApi partidaServiceApi;

    @RequestMapping("/LibroDiario")
    public String incioVistaDiario(Model model){
        model.addAttribute("list", partidaServiceApi.getAll());

        return "LibroDiario";
    }

}
