package com.SistemaContable.Controller.AlejandroDeras;

import com.SistemaContable.Repository.AlejandroDeras.MayorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class MayorControler {

    @Autowired
    private MayorRepositorio mayorRepositorio;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*
    @GetMapping("/LibroMayor")
    public String viewMayor(Model model){
        String sql = "SELECT c.codigo, c.nombre, concat(EXTRACT(DAY from p.fecha),'/',EXTRACT(MONTH from p.fecha),'/',EXTRACT(YEAR from p.fecha))AS fecha, p.descripcion, rp.debe, rp.haber, CASE WHEN (c.saldo_cuenta) = 'Deudor' THEN (SUM(rp.debe) - SUM(rp.haber)) ELSE (SUM(rp.haber) - SUM(rp.debe)) END AS saldo FROM Partida p INNER JOIN registro_partida rp ON p.id = rp.partida_id INNER JOIN catalogo c ON c.id = rp.catalogo_id GROUP BY c.codigo, c.nombre, p.fecha, p.descripcion, rp.debe,rp.haber, c.saldo_cuenta ORDER BY c.codigo";
        List<Map<String, Object>> resultado = jdbcTemplate.queryForList(sql);
        model.addAttribute("resultado", resultado);
        return "LibroMayor";
    }
    */

}
