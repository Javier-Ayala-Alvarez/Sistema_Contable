package com.SistemaContable.Controller.JavierAyala;

import com.SistemaContable.Repository.JavierAyala.Interfaces.CatalogoRepositoryInt;
import com.SistemaContable.model.JavierAyala.Catalogo;
import com.SistemaContable.servicio.JavierAyala.ServicesImplements.CatalogoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
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

    @Autowired
    private CatalogoRepositoryInt catalogoRespositoryInt;

    @GetMapping()
    public String catalogo(@RequestParam Map<String, Object> params, Model model, Catalogo catalogo, String buscar) {
        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;//obteniendo la cantidad de paginas
        PageRequest pageRequest = PageRequest.of(page, 5, Sort.by("codigo").ascending()); //pagina que va a buscar y el numero de registros ademas ordena la pagina

        Page<Catalogo> pageCatalogo = catalogoServices.mostrarCatalogo(buscar, pageRequest); //la pagina
        int totalPage = pageCatalogo.getTotalPages(); //total de pagina
        if (totalPage > 0) {
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pages", pages);//lista de paginas
        } else {
            model.addAttribute("pie", "EL catálogo está vacío debe registrar datos");
        }
        model.addAttribute("catalogos", pageCatalogo.getContent());//enviando la lista
        model.addAttribute("current", page + 1);//
        model.addAttribute("next", page + 2);
        model.addAttribute("prev", page);
        model.addAttribute("last", totalPage);//obteniendo el parametro
        model.addAttribute("tituloDeLaPagina", "Catálogo");
        model.addAttribute("buscar", buscar);
        return "catalogo";

    }

    @PostMapping()
    public String save(@Validated Catalogo catalogo, BindingResult bindingResult, RedirectAttributes redirect, Model model) {
        if (catalogo.getId() == null) {

            if (catalogo.getSaldoCuenta() != "" && catalogo.getTipoCuenta() != ""
                    && catalogoServices.buscar("codigo", catalogo.getCodigo()) == "true"
                    && catalogoServices.buscar("nombre", catalogo.getNombre()) == "true") {

                if (catalogoServices.validarCodigo(catalogo.getCodigo()) == true) {
                    if (bindingResult.hasErrors()) {
                        model.addAttribute("catalogo", catalogo);
                        return "Catalogo";
                    }
                    catalogoServices.save(catalogo);
                    redirect.addFlashAttribute("msgExito", "activo");
                    return "redirect:/Catalogo";

                } else {
                    redirect.addFlashAttribute("msgErrorNoExisteDatos", "activo");
                    return "redirect:/Catalogo";
                }
            } else {
                redirect.addFlashAttribute("msgErrorDatos", "activo");
                return "redirect:/Catalogo";
            }

        } else {
            redirect.addFlashAttribute("msgExito", "activo");
            return "redirect:/Catalogo";
        }


    }


    @PostMapping("/modificar")
    public String modificar(@RequestParam("codigo") String codigo,
                            @RequestParam("nombre") String nombre,
                            @RequestParam("saldo_cuenta") String saldo_cuenta,
                            @RequestParam("tipo_cuenta") String tipo_cuenta,
                            Catalogo catalogo, RedirectAttributes redirect) {

        catalogo = catalogoRespositoryInt.buscarCodigo(codigo);
        catalogo.setNombre(nombre);
        catalogo.setSaldoCuenta(saldo_cuenta);
        catalogo.setTipoCuenta(tipo_cuenta);
        if (catalogo.getSaldoCuenta() != "" && catalogo.getTipoCuenta() != ""
                && catalogoServices.buscar("nombre", catalogo.getNombre()) == "true") {
            catalogoServices.save(catalogo);
            redirect.addFlashAttribute("msgExito", "activo");
            return "redirect:/Catalogo";
        }else {
            redirect.addFlashAttribute("msgErrorDatos", "activo");
            return "redirect:/Catalogo";
        }


    }

    @PostMapping("/{id}/eliminar")
    public String eliminarContacto(@PathVariable Integer id, RedirectAttributes redirect) {
        Catalogo catalogo = catalogoRespositoryInt.getById(id);//Busca por id
        int size = catalogoServices.searchLike(catalogo.getCodigo());
        if (size == 1) {
            catalogoServices.delete(catalogo);//Eliminado
            redirect.addFlashAttribute("msgExito", "activo");
        } else {
            redirect.addFlashAttribute("msgErrorELiminarCuenta", "activo");
        }

        return "redirect:/Catalogo";
    }


}
