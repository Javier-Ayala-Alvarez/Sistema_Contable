//Está clase se dedica a redirecionar las diferentes paginas segun el boton  que se seleccione o precione


package com.SistemaContable.Controller;

import com.SistemaContable.Controller.dto.UsuarioRegistroDTO;
import com.SistemaContable.model.Catalogo;
import com.SistemaContable.servicio.CatalogoServices;
import com.SistemaContable.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class RegistroControlador {

    //@Autowired es una dependencia para inyectar una clase en otra
    @Autowired
    private UsuarioServicio servicio;
    @Autowired
    private CatalogoServices catalogoServices;


    //@GetMappin permite definir la url que ejecutando hara el proceso que le indique el metodo,
    // en este caso direccionara a login.
    @GetMapping("/login")
    public String iniciarSesion() {
        return "login";
    }

    //@GetMapping va a enviar datos a la página principal
    @GetMapping("/")
    public String verPaginaDeInicio(Model modelo) {
        modelo.addAttribute("usuarios", "Usuario");
        return "index";
    }

    //@GetMapping va a direccionar al catálogo cuando la url defina eso despues de /
    @GetMapping("/Catalogo")
    public String catalogo(@RequestParam Map<String, Object> params, Model model) {
        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;//obteniendo la cantidad de paginas
        PageRequest pageRequest = PageRequest.of(page, 5, Sort.by("codigo").ascending()); //pagina que va a buscar y el numero de registros ademas ordena la pagina
        Page<Catalogo> pageCatalogo = catalogoServices.mostrarCatalogo(pageRequest); //la pagina
        int totalPage = pageCatalogo.getTotalPages(); //total de pagina
        if (totalPage > 0) {
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pages", pages);//lista de paginas
        }
        model.addAttribute("catalogos", pageCatalogo.getContent());//enviando la lista
        model.addAttribute("current", page + 1);//
        model.addAttribute("next", page + 2);
        model.addAttribute("prev", page);
        model.addAttribute("last", totalPage);//obteniendo el parametro
        model.addAttribute("tituloDeLaPagina", "Catálogo");
        return "catalogo";

    }


    @PostMapping("/buscarcatalogo")
    public String BuscarCatalogo(@PathVariable String buscar, Model model) {
        model.addAttribute("tituloDeLaPagina", "Catálogo");
        List<Catalogo> catalogo = catalogoServices.buscar(buscar);
        model.addAttribute("catalogos", catalogo);
        return "catalogo";

    }

    @GetMapping("/LibroDiario")
    public String LibroDiario(Model model) {
        model.addAttribute("tituloDeLaPagina", "Libro Diario");
        return "LibroDiario";
    }

    @GetMapping("/LibroMayor")
    public String LibroMayor(Model model) {
        model.addAttribute("tituloDeLaPagina", "Libro Mayor");
        return "LibroMayor";
    }

    @GetMapping("/BalanceDeComprobacion")
    public String BalanceDeComprobacion(Model model) {
        model.addAttribute("tituloDeLaPagina", "Balance de comprobación");
        return "BalanceDeComprobacion";
    }

    @GetMapping("/EstadoDeResultado")
    public String EstadoDeResultado(Model model) {
        model.addAttribute("tituloDeLaPagina", "Estado de Resultado");
        return "EstadoDeResultado";
    }

    @GetMapping("/BalanceGeneral")
    public String BalanceGeneral(Model model) {
        model.addAttribute("tituloDeLaPagina", "Balance General");
        return "BalanceGeneral";
    }


}
