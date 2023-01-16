package com.SistemaContable.Controller.AlejandroDeras;

import com.SistemaContable.Repository.AlejandroDeras.MayorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MayorControler {

    @Autowired
    private MayorRepositorio mayorRepositorio;

    @RequestMapping("/LibroMayor")
    public String viewMayor(Model model){
        model.addAttribute("mayorList", mayorRepositorio.libroMayor());
        return "LibroMayor";
    }


}
