package com.SistemaContable.Controller.DiegoMejia;

import com.SistemaContable.servicio.DiegoMejia.Interfaces.PartidaServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BalanceGeneralController {
    @Autowired
    PartidaServiceApi partidaServiceApi;
    @RequestMapping("/BalanceGeneral")
    public String balanceGeneral(Model model){
       model.addAttribute("cuentas",partidaServiceApi.balanceGeneral());
        return "BalanceGeneral";
    }
}
