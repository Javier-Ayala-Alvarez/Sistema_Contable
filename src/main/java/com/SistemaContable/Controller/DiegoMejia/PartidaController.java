package com.SistemaContable.Controller.DiegoMejia;

import com.SistemaContable.Repository.DiegoMejia.EmpresaRepository;
import com.SistemaContable.Repository.JavierAyala.Interfaces.CicloContableReposytory;
import com.SistemaContable.model.DiegoMejia.Partida;
import com.SistemaContable.model.DiegoMejia.RegistroPartida;
import com.SistemaContable.model.JavierAyala.CicloContable;
import com.SistemaContable.model.JavierAyala.Empresa;
import com.SistemaContable.servicio.DiegoMejia.Interfaces.PartidaServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Controller
public class PartidaController {
    @Autowired
    private PartidaServiceApi partidaServiceApi;

    @Autowired
    CicloContableReposytory cicloContableReposytory;

    @Autowired
    EmpresaRepository empresaRepository;

    @RequestMapping("/LibroDiario")
    public String startViewDiario(Model model) {

        Optional<CicloContable> obj = cicloContableReposytory.findFirstByOrderByIdDesc();
        if (!obj.isPresent()) {
            return "redirect:/";
        }
        model.addAttribute("tituloDeLaPagina", "Libro Diario");

        model.addAttribute("list", partidaServiceApi.getAll());

        return "/LibroDiario";
    }

    @GetMapping("/LibroDiario/detalle/{id}")
    public String getPartidaById(Model model, @PathVariable("id") String id) {

        model.addAttribute("partidaRegistro", partidaServiceApi.get(Long.valueOf(id)));

        return "/fragment/modalRegistrosPartida.html:: modalRegistroPartida";
    }


    @PostMapping("/LibroDiario/save")
    public String savePartida(Partida partida, Model model, HttpSession sesion) {

        if (partida.getDescripcion().isEmpty()) {
            return "redirect:/LibroDiario";
        }


        Optional<Long> checkNull = Optional.ofNullable(partidaServiceApi.getLastId());
        Long id = checkNull.isPresent() ? partidaServiceApi.getLastId() + 1 : 1;
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
        BigDecimal bDebe = new BigDecimal(debe);
        BigDecimal bHaber = new BigDecimal(haber);

        Optional<CicloContable> objCiclo =cicloContableReposytory.findFirstByOrderByIdDesc();
        Optional<Empresa> objEmpresa = Optional.ofNullable(empresaRepository.findFirstByOrderByIdDesc());
        if (!objCiclo.isPresent() || !objEmpresa.isPresent()) {
            return "redirect:/";
        }

        if (bHaber.compareTo(bDebe) != 0) {
            return "redirect:/LibroDiario/registroPartida";
        }

        Partida p = (Partida) session.getAttribute("partidaSesion");
        p.setCicloContable(objCiclo.get());
        p.setEmpresa(objEmpresa.get());
        partidaServiceApi.save(p);
        return "redirect:/LibroDiario";
    }

}
