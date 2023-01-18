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
        //String sql = "SELECT c.codigo, c.nombre, concat(EXTRACT(DAY from p.fecha),'/',EXTRACT(MONTH from p.fecha),'/',EXTRACT(YEAR from p.fecha))AS fecha, p.descripcion, rp.debe, rp.haber, CASE WHEN (c.saldo_cuenta) = 'Deudor' THEN (SUM(rp.debe) - SUM(rp.haber)) ELSE (SUM(rp.haber) - SUM(rp.debe)) END AS saldo FROM Partida p INNER JOIN registro_partida rp ON p.id = rp.partida_id INNER JOIN catalogo c ON c.id = rp.catalogo_id GROUP BY c.codigo, c.nombre, p.fecha, p.descripcion, rp.debe,rp.haber, c.saldo_cuenta ORDER BY c.codigo";
        //String sql = "SELECT c.codigo, c.nombre, concat(EXTRACT(DAY from p.fecha),'/',EXTRACT(MONTH from p.fecha),'/',EXTRACT(YEAR from p.fecha))AS fecha, p.descripcion, rp.debe, rp.haber, SUM(CASE WHEN c.saldo_cuenta = 'Deudor' THEN rp.debe - rp.haber WHEN c.saldo_cuenta = 'Acreedor' THEN rp.haber - rp.debe ELSE 0 END) OVER (PARTITION BY c.nombre ORDER BY p.fecha) AS saldo FROM Partida p INNER JOIN registro_partida rp ON p.id = rp.partida_id INNER JOIN catalogo c ON c.id = rp.catalogo_id GROUP BY c.codigo, c.nombre, p.fecha, p.descripcion, rp.debe,rp.haber, c.saldo_cuenta ORDER BY c.nombre";
        String sql = "SELECT c.codigo, c.nombre, EXTRACT(MONTH from p.fecha) AS mes, EXTRACT(YEAR from p.fecha) AS anio,concat(EXTRACT(DAY from p.fecha),'/',EXTRACT(MONTH from p.fecha),'/',EXTRACT(YEAR from p.fecha))AS fecha, p.descripcion, rp.debe, rp.haber, SUM(CASE WHEN c.saldo_cuenta = 'Deudor' THEN rp.debe - rp.haber WHEN c.saldo_cuenta = 'Acreedor' THEN rp.haber - rp.debe ELSE 0 END) OVER (PARTITION BY c.nombre ORDER BY p.fecha) AS saldo FROM Partida p INNER JOIN registro_partida rp ON p.id = rp.partida_id INNER JOIN catalogo c ON c.id = rp.catalogo_id WHERE EXTRACT(MONTH from p.fecha) = '1' AND EXTRACT(YEAR from p.fecha) = '2023' GROUP BY c.codigo, c.nombre, p.fecha, p.descripcion, rp.debe,rp.haber, c.saldo_cuenta ORDER BY c.nombre";
        List<Map<String, Object>> mayor = jdbcTemplate.queryForList(sql);
        model.addAttribute("mayor", mayor);

        //NOMBRE EMPRESA
        String sql3 = "SELECT UPPER(nombre_empresa) as nombre_empresa FROM empresa WHERE id = ?";
        String nombreEmpresa = jdbcTemplate.queryForObject(sql3, new Object[]{1}, String.class);
        model.addAttribute("nombreEmpresa", nombreEmpresa);

        //MES
        String sql4 = "SELECT (EXTRACT(MONTH from p.fecha)) AS mes FROM partida p GROUP BY mes ORDER BY EXTRACT(MONTH from p.fecha) DESC";
        List<Map<String, Object>> meses = jdbcTemplate.queryForList(sql4);
        model.addAttribute("meses", meses);

        return "LibroMayor";
    }
    @GetMapping("/Cierre")
    public String cierre(Model model) {
        model.addAttribute("tituloDeLaPagina", "Cierre contable");
        return "cierre";
    }

    @GetMapping("/BalanceDeComprobacion")
    public String BalanceDeComprobacion(Model model) {
        model.addAttribute("tituloDeLaPagina", "Balance de comprobación: Gerson");
        return "BalanceDeComprobacion";
    }




}
