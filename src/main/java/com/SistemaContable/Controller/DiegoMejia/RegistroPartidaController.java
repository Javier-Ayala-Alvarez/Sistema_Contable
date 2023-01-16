package com.SistemaContable.Controller.DiegoMejia;

import com.SistemaContable.Repository.JavierAyala.Interfaces.CatalogoRepositoryInt;
import com.SistemaContable.Repository.JavierAyala.Interfaces.CicloContableReposytory;
import com.SistemaContable.model.DiegoMejia.Partida;
import com.SistemaContable.model.DiegoMejia.RegistroPartida;
import com.SistemaContable.model.JavierAyala.Catalogo;
import com.SistemaContable.model.JavierAyala.CicloContable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Optional;


@Controller
public class RegistroPartidaController {

    @Autowired
    private CatalogoRepositoryInt catalogoRespositoryInt;
    @Autowired CicloContableReposytory cicloContableReposytory;

    @RequestMapping("/LibroDiario/registroPartida")
    String registroPartida(Model model, HttpSession session) {


        if (session.getAttribute("partidaSesion") == null) {
            return "redirect:/LibroDiario";
        }

        Partida p = (Partida) session.getAttribute("partidaSesion");

        //suma de los valores debe y haber para luego mostrar en la vista
        BigDecimal sDebe = new BigDecimal("0");
        BigDecimal sHaber = new BigDecimal("0");
        for (RegistroPartida registroPartida: p.getRegistroPartidas()) {
           sDebe = sDebe.add(registroPartida.getDebe());
           sHaber =  sHaber.add(registroPartida.getHaber());
        }

        //instancia para ser utilizada en el formulario de la vista
        RegistroPartida registroPartida = new RegistroPartida();


        model.addAttribute("registro", registroPartida);
        model.addAttribute("p", p);
        model.addAttribute("haber", sHaber);
        model.addAttribute("debe", sDebe);
        return "RegistroPartida";


    }

    @PostMapping("/LibroDiario/registroPartida/add")
    String addRegistroPartida(HttpSession session, RegistroPartida registroPartida) {


        if (session.getAttribute("partidaSesion") == null ) {
            return "redirect:/LibroDiario";
        }
        Partida p = (Partida) session.getAttribute("partidaSesion");
        Catalogo c = catalogoRespositoryInt.buscarCodigo(registroPartida.getCatalogo().getCodigo());
        Optional<Catalogo> oC = Optional.ofNullable(c);
        if (!oC.isPresent()){
            return "redirect:/LibroDiario/registroPartida";
        }

        registroPartida.setCatalogo(c);
        registroPartida.setPartida(p);
        p.getRegistroPartidas().add(registroPartida);
        return "redirect:/LibroDiario/registroPartida";

    }

    @GetMapping("/LibroDiario/registroPartida/delete/{id}")
    String deleteRegistroPartida(@PathVariable("id")  String id, HttpSession session){
        if (session.getAttribute("partidaSesion") == null) {
            return "redirect:/LibroDiario";
        }

        Partida p = (Partida) session.getAttribute("partidaSesion");
        RegistroPartida r = p.getRegistroPartidas().get(Integer.valueOf(id));
        if(p.getRegistroPartidas().remove(r)){
            System.out.println("exito");
        }

        return "redirect:/LibroDiario/registroPartida";
    }
}
