package com.SistemaContable.Controller.Gerson;

import com.SistemaContable.Repository.Gerson.DataEmpresaRepositorio;
import com.SistemaContable.model.Gerson.DataEmpresa;
import com.SistemaContable.model.JavierAyala.Empresa;
import com.SistemaContable.servicio.Gerson.DataEmpresaInterfaz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/Configuracion")
public class DataEmpresaController {
    @Autowired
    DataEmpresaRepositorio dataEmpresaRepositorio;

    @GetMapping("/Configuracion")
    public String Configuracion(Model model) {
        Empresa dataEmpresa = new Empresa();
        if(dataEmpresaRepositorio.findAll().size() > 0){
            dataEmpresa =dataEmpresaRepositorio.findAll().get(dataEmpresaRepositorio.findAll().size()-1);

        }
        model.addAttribute("dataEmpresa",dataEmpresa );

        //model.addAttribute("tituloDeLaPagina", "Configuración: Gerson");
        return "configuracion";
    }


    @PostMapping("/ConfiguracionGuardar")
    public String crearEmpresa(Model modelo,Empresa empresa ){
        Empresa dataEmpresa = new Empresa();
        dataEmpresaRepositorio.save(empresa);
        if(dataEmpresaRepositorio.findAll().size() > 0){
            dataEmpresa =dataEmpresaRepositorio.findAll().get(dataEmpresaRepositorio.findAll().size()-1);

        }
        modelo.addAttribute("dataEmpresa", dataEmpresa);
        //modelo.addAttribute("tituloDeLaPagina", "Configuración: Gerson");

        //return "dataEmpresaCrear";
        return "configuracion";
    }
}
