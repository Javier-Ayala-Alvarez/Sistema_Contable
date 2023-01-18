package com.SistemaContable.Controller.Gerson;

import com.SistemaContable.Repository.JavierAyala.Interfaces.CicloContableReposytory;
import com.SistemaContable.model.JavierAyala.CicloContable;
import com.SistemaContable.servicio.DiegoMejia.Interfaces.PartidaServiceApi;
import com.SistemaContable.servicio.Gerson.BalanzaDeComprobacionInterfaz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.Optional;

@Controller
public class BalanzaDeComprobacionController {

    @Autowired
    PartidaServiceApi partidaServiceApi;
    @Autowired
    CicloContableReposytory cicloContableReposytory;

    @RequestMapping("/BalanceDeComprobacion")
    public String balanceGeneral(Model model){
        Optional<CicloContable> objCiclo =cicloContableReposytory.findFirstByOrderByIdDesc();
        if (!objCiclo.isPresent()) {
            return "redirect:/";
        }

        model.addAttribute("cuentas",partidaServiceApi.mayorizar(objCiclo.get().getId()));
        BigDecimal debeacum = new BigDecimal("0");
        BigDecimal haberacum = new BigDecimal("0");
        for (int i=0; i<partidaServiceApi.mayorizar(objCiclo.get().getId()).size()-1; i++){
            debeacum = debeacum.add(partidaServiceApi.mayorizar(objCiclo.get().getId()).get(i).getDebe());
            haberacum = haberacum.add(partidaServiceApi.mayorizar(objCiclo.get().getId()).get(i).getHaber());
        }
        model.addAttribute("debeacum", debeacum);
        model.addAttribute("haberacum", haberacum);
        return "BalanceDeComprobacion";
    }

}
