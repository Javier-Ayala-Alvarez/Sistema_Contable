package com.SistemaContable.Controller.JavierAyala;

import com.SistemaContable.Repository.DiegoMejia.dao.Api.PartidaDaoApi;
import com.SistemaContable.Repository.JavierAyala.Interfaces.CicloContableReposytory;
import com.SistemaContable.model.JavierAyala.RegistrosEstadosResultado;
import com.SistemaContable.model.JavierAyala.RegistrosEstadosResultadoDAO;
import com.SistemaContable.servicio.DiegoMejia.Interfaces.PartidaServiceApi;
import com.SistemaContable.servicio.JavierAyala.ServicesImplements.EstadoResultadoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EstadoResultadoController {

    @Autowired
    EstadoResultadoImpl estadoResultado;

    @Autowired
    CicloContableReposytory cicloContableReposytory;

    @GetMapping("/EstadoDeResultado")
    public String EstadoDeResultado(Model model, Integer dato) {

        List<RegistrosEstadosResultadoDAO> estado = new ArrayList<>();
        List<RegistrosEstadosResultado> registrosBase = new ArrayList<>();
        registrosBase = this.estadoResultado.mostrar(dato);

        registrosBase.stream().forEach(c -> {
            if (c.getCatalogo().getCodigo().equals("5")) {
                RegistrosEstadosResultadoDAO registro = new RegistrosEstadosResultadoDAO(
                        //Integer id, Float total_cuentas, Float total_cuentas_tercer, Float total_cuentas_segundo, Catalogo catalogo, CicloContable cicloContable
                        c.getId(),
                        c.getTotal_cuentas(),
                        c.getTotal_cuentas_tercer(),
                        c.getTotal_cuentas_segundo(),
                        c.getCatalogo(),
                        c.getCicloContable());
                estado.add(registro);
            }

        });
        model.addAttribute("tituloDeLaPagina", "Estado de Resultado");
        model.addAttribute("estados", registrosBase);
        model.addAttribute("anios", cicloContableReposytory.findAll());
        return "EstadoDeResultado";
    }
}
