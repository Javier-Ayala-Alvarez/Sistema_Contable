package com.SistemaContable.Controller;

import com.SistemaContable.model.Catalogo;
import com.SistemaContable.servicio.CatalogoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/Catalogo")
public class CatalogoController {
    @Autowired
    private CatalogoServices catalogoServices;


    @GetMapping()
    public String catalogo(@RequestParam Map<String, Object> params, Model model, Catalogo catalogo) {
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

    @PostMapping()
    public String save(@Validated Catalogo catalogo, BindingResult bindingResult, RedirectAttributes redirect, Model model) {

            if (catalogoServices.buscar("codigo", catalogo.getCodigo()) == "true" && catalogoServices.buscar("nombre", catalogo.getNombre()) == "true") {
                    if (bindingResult.hasErrors()) {
                        model.addAttribute("catalogo", catalogo);
                        return "Catalogo";
                    }

                    catalogo.setEstado(true);
                    catalogoServices.save(catalogo);
                    redirect.addFlashAttribute("msgExito", "activo");
                    return "redirect:/Catalogo";


            } else {
                redirect.addFlashAttribute("msgErrorDatos", "activo");
                return "redirect:/Catalogo";
            }



    }

    @PostMapping("/buscarcatalogo")
    public String BuscarCatalogo(@PathVariable String buscar, Model model) {
        model.addAttribute("tituloDeLaPagina", "Catálogo");
        List<Catalogo> catalogo = catalogoServices.buscar(buscar);
        model.addAttribute("catalogos", catalogo);
        return "catalogo";

    }
}
