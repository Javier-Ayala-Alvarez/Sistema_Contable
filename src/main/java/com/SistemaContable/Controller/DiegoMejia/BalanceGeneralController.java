package com.SistemaContable.Controller.DiegoMejia;

import com.SistemaContable.model.DiegoMejia.MayorDTO;
import com.SistemaContable.servicio.DiegoMejia.Interfaces.PartidaServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.ArrayList;

@Controller
public class BalanceGeneralController {
    @Autowired
    PartidaServiceApi partidaServiceApi;

    @RequestMapping("/BalanceGeneral")
    public String balanceGeneral(Model model) {
        ArrayList<MayorDTO> mayorDTOArrayList = partidaServiceApi.balanceGeneral();
        BigDecimal debe = new BigDecimal("0");
        BigDecimal haber = new BigDecimal("0");

        for (MayorDTO obj : mayorDTOArrayList) {
            debe = debe.add(obj.getDebe());

            haber = haber.add(obj.getHaber());

        }
        System.out.println("debe " + debe);
        System.out.println("haber" + haber);

        model.addAttribute("cuentas", mayorDTOArrayList);
        model.addAttribute("debe", debe);
        model.addAttribute("haber", haber);
        return "BalanceGeneral";
    }
}
