package com.SistemaContable.Controller.JavierAyala;

import com.SistemaContable.Repository.DiegoMejia.PartidaRepository;
import com.SistemaContable.Repository.JavierAyala.Interfaces.CatalogoRepositoryInt;
import com.SistemaContable.Repository.JavierAyala.Interfaces.CicloContableReposytory;
import com.SistemaContable.Repository.JavierAyala.Interfaces.EstadoResultadoPercistencia;
import com.SistemaContable.Repository.JavierAyala.Interfaces.RegistrosPartidaRepImp;
import com.SistemaContable.model.JavierAyala.EstadoResultado;
import com.SistemaContable.model.JavierAyala.RegistrosEstadosResultado;
import com.SistemaContable.model.JavierAyala.RegistrosEstadosResultadoDAO;
import com.SistemaContable.servicio.JavierAyala.Interfaces.EstadoResultadoCargarIn;
import com.SistemaContable.servicio.JavierAyala.ServicesImplements.EstadoResultadoCargarIm;
import com.SistemaContable.servicio.JavierAyala.ServicesImplements.EstadoResultadoImpl;
import net.sf.jasperreports.engine.JRException;
import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class EstadoResultadoController {

    @Autowired
    EstadoResultadoImpl estadoResultado;

    @Autowired
    CicloContableReposytory cicloContableReposytory;

    @Autowired
    PartidaRepository partidaRepository;

    @Autowired
    CatalogoRepositoryInt catalogoRepositoryInt;

    @Autowired
    RegistrosPartidaRepImp registrosPartidaRepImp;

    @Autowired
    EstadoResultadoPercistencia estadoResultadoPercistencia;
    @Autowired
            EstadoResultadoCargarIn estadoResultadoCargarIn;
    @Autowired
    EstadoResultadoCargarIm estadoResultadoCargarIm;
    Double comprasNetas = 0.0;
    Double balanceIncial = 0.0;
    Double mercancia = 0.0;
    Double balance = 0.0;
    Double ventasNetas = 0.0;
    Double costoVenta = 0.0;
    Double utilidadBruta = 0.0;
    Double gastosOperaciones = 0.0;
    @GetMapping("/reporte")
    public ResponseEntity<byte[]> getCatalogoReportPDF() throws JRException, FileNotFoundException {

        return estadoResultadoCargarIm.exportReport();

    }

    @GetMapping("/EstadoDeResultado")
    public String EstadoDeResultado(@RequestParam Map<String, Object> params, Model model, String dato) {
        List<RegistrosEstadosResultado> registrosBase = new ArrayList<>();
        int anio[] = cicloContableReposytory.anioactual();
        //-----------------------------------------------Areglar fecha-------------------------------------//

        try {

            registrosBase = this.estadoResultado.mostrar((dato ==null)?anio[0]:Integer.parseInt(dato));
            estadoResultadoCargarIn.cargaDatosEstado((dato ==null)?anio[0]:Integer.parseInt(dato));
            List<EstadoResultado> estadoResultado =  estadoResultadoPercistencia.findAll();
            model.addAttribute("tituloDeLaPagina", "Estado de Resultado");
            model.addAttribute("estados", estadoResultado);
            model.addAttribute("anioActual", registrosBase.get(0).getCicloContable().getFecha_fin());
            model.addAttribute("anios", cicloContableReposytory.findAll());
            model.addAttribute("pie", "");

        }catch (Exception e){
            model.addAttribute("pie", "Revise sus busqueda, los errores se pueden deber a que el año ingresado no contiene partidas");
        }


        return "EstadoDeResultado";
    }
}
