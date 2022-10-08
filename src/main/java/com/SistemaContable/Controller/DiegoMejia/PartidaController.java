package com.SistemaContable.Controller.DiegoMejia;

import com.SistemaContable.model.DiegoMejia.Partida;
import com.SistemaContable.model.DiegoMejia.RegistroPartida;
import com.SistemaContable.servicio.DiegoMejia.Interfaces.PartidaServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Controller
public class PartidaController {
    @Autowired
    private PartidaServiceApi partidaServiceApi;

    @RequestMapping("/LibroDiario")
    public String startViewDiario(Model model, HttpSession session) {

        model.addAttribute("tituloDeLaPagina", "Libro Diario");

        model.addAttribute("list", partidaServiceApi.getAll());

        return "/LibroDiario";
    }

    @PostMapping("/LibroDiario/save")
    public String savePartida(Partida partida, Model model, HttpSession sesion) {

        if (partida.getDescripcion().isEmpty()) {
            return "redirect:/LibroDiario";
        }


        Optional<Long> checkNull = Optional.ofNullable(partidaServiceApi.getLastId());
        Long id = checkNull.isPresent() ? partidaServiceApi.getLastId() +1: 1;
        partida.setId(id);
        partida.setFecha(new Date());
        partida.setActivo(true);
        partida.setRegistroPartidas(new ArrayList<>());


        sesion.setAttribute("partidaSesion", partida);

        return "redirect:/LibroDiario/registroPartida";
    }

    @GetMapping("/LibroDiario/modal")
    public String sendModalPartida(Model model) {
        model.addAttribute("partida", new Partida());
        return "modalNuevaPartida.html :: modalPartida";
    }

    @PostMapping("/LibroDiario/saveBD")
    public String savePartidaBD(
            @RequestParam("nameDebe") String debe,
            @RequestParam("nameHaber") String haber,
            HttpSession session) {

        if (!debe.equals(haber)){
            return "redirect:/LibroDiario/registroPartida";
        }

        Partida p = (Partida) session.getAttribute("partidaSesion");
        partidaServiceApi.save(p);
        return "redirect:/LibroDiario";
    }

}
