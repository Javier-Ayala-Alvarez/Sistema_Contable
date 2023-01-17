//Está clase se dedica a redirecionar las diferentes paginas segun el boton  que se seleccione o precione


package com.SistemaContable.Controller;

import com.SistemaContable.servicio.JavierAyala.Interfaces.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller

public class RegistroController {

    //@Autowired es una dependencia para inyectar una clase en otra
    @Autowired
    private UsuarioServicio servicio;

    @Autowired
    private JdbcTemplate jdbcTemplate;





    //@GetMappin permite definir la url que ejecutando hara el proceso que le indique el metodo,
    // en este caso direccionara a login.
    @GetMapping("/login")
    public String iniciarSesion() {
        return "login";
    }

    //@GetMapping va a enviar datos a la página principal
    @GetMapping("/")

    public String verPaginaDeInicio(Model modelo) {
        modelo.addAttribute("usuario", "usuarioServicio");
        return "index";
    }

    //@GetMapping va a direccionar al catálogo cuando la url defina eso despues de /


    /*@GetMapping("/LibroDiario")
    public String LibroDiario(Model model) {
        model.addAttribute("tituloDeLaPagina", "Libro Diario: Diego Mejia");
        return "LibroDiario";
<<<<<<< HEAD
    }*/



    @GetMapping("/LibroMayor")
    public String LibroMayor(Model model) {
        model.addAttribute("tituloDeLaPagina", "Libro Mayor");

        //CONSULTA LIBRO MAYOR
        String sql = "SELECT c.codigo, c.nombre, concat(EXTRACT(DAY from p.fecha),'/',EXTRACT(MONTH from p.fecha),'/',EXTRACT(YEAR from p.fecha))AS fecha, p.descripcion, rp.debe, rp.haber, CASE WHEN (c.saldo_cuenta) = 'Deudor' THEN (SUM(rp.debe) - SUM(rp.haber)) ELSE (SUM(rp.haber) - SUM(rp.debe)) END AS saldo FROM Partida p INNER JOIN registro_partida rp ON p.id = rp.partida_id INNER JOIN catalogo c ON c.id = rp.catalogo_id GROUP BY c.codigo, c.nombre, p.fecha, p.descripcion, rp.debe,rp.haber, c.saldo_cuenta ORDER BY c.codigo";
        List<Map<String, Object>> mayor = jdbcTemplate.queryForList(sql);
        model.addAttribute("mayor", mayor);

        //ENCABEZADO MAYOR
        String sql2 = "SELECT c.codigo, c.nombre\n" +
                "FROM Partida p \n" +
                "INNER JOIN registro_partida rp ON p.id = rp.partida_id \n" +
                "INNER JOIN catalogo c ON c.id = rp.catalogo_id \n" +
                "GROUP BY c.codigo, c.nombre\n" +
                "ORDER BY c.codigo";
        List<Map<String, Object>> eMayor = jdbcTemplate.queryForList(sql2);
        model.addAttribute("eMayor", eMayor);

        String sql3 = "SELECT UPPER(nombre_empresa) as nombre_empresa FROM empresa WHERE id = ?";
        String nombreEmpresa = jdbcTemplate.queryForObject(sql3, new Object[]{1}, String.class);
        model.addAttribute("nombreEmpresa", nombreEmpresa);

        return "LibroMayor";
    }
    @GetMapping("/Cierre")
    public String cierre(Model model) {
        model.addAttribute("tituloDeLaPagina", "Cierre contable: Deras");
        return "cierre";
    }

    @GetMapping("/BalanceDeComprobacion")
    public String BalanceDeComprobacion(Model model) {
        model.addAttribute("tituloDeLaPagina", "Balance de comprobación: Gerson");
        return "BalanceDeComprobacion";
    }




}
