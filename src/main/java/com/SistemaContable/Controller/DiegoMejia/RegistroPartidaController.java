package com.SistemaContable.Controller.DiegoMejia;

import com.SistemaContable.model.DiegoMejia.Partida;
import com.SistemaContable.model.DiegoMejia.RegistroPartida;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class RegistroPartidaController {

    @RequestMapping("/LibroDiario/registroPartida")
    String registroPartida(Model model, HttpSession session) {



        if (session.getAttribute("partidaSesion") == null){
            return "redirect:/LibroDiario";
        }
        Partida  p = (Partida)session.getAttribute("partidaSesion");
        RegistroPartida registroPartida = new RegistroPartida();
        model.addAttribute("registro", registroPartida);
        model.addAttribute("p",p);
        return "RegistroPartida";


    }
    @PostMapping("/LibroDiario/registroPartida/add")
    String addRegistroPartida(HttpSession session, RegistroPartida registroPartida){

        if (session.getAttribute("partidaSesion") == null){
            return "redirect:/LibroDiario";
        }
        Partida  p = (Partida)session.getAttribute("partidaSesion");
        p.getRegistroPartidas().add(registroPartida);
        return "redirect:/LibroDiario/registroPartida";

    }
}
