package com.SistemaContable.Controller.Gerson;

import com.SistemaContable.servicio.DiegoMejia.Interfaces.PartidaServiceApi;
import com.SistemaContable.servicio.Gerson.BalanzaDeComprobacionInterfaz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

@Controller
public class BalanzaDeComprobacionController {

    @Autowired
    PartidaServiceApi partidaServiceApi;

    @RequestMapping("/BalanceDeComprobacion")
    public String balanceGeneral(Model model){
        model.addAttribute("cuentas",partidaServiceApi.mayorizar());
        BigDecimal debeacum = new BigDecimal("0");
        BigDecimal haberacum = new BigDecimal("0");
        for (int i=0; i<partidaServiceApi.mayorizar().size()-1; i++){
            debeacum = debeacum.add(partidaServiceApi.mayorizar().get(i).getDebe());
            haberacum = haberacum.add(partidaServiceApi.mayorizar().get(i).getHaber());
        }
        model.addAttribute("debeacum", debeacum);
        model.addAttribute("haberacum", haberacum);
        return "BalanceDeComprobacion";
    }

}
