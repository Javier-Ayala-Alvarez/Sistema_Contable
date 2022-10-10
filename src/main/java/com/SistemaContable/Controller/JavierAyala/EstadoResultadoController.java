package com.SistemaContable.Controller.JavierAyala;

import com.SistemaContable.Repository.DiegoMejia.PartidaRepository;
import com.SistemaContable.Repository.JavierAyala.Interfaces.CatalogoRepositoryInt;
import com.SistemaContable.Repository.JavierAyala.Interfaces.CicloContableReposytory;
import com.SistemaContable.Repository.JavierAyala.Interfaces.RegistrosPartidaRepImp;
import com.SistemaContable.model.JavierAyala.RegistrosEstadosResultado;
import com.SistemaContable.model.JavierAyala.RegistrosEstadosResultadoDAO;
import com.SistemaContable.servicio.JavierAyala.ServicesImplements.EstadoResultadoImpl;
import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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
    Double comprasNetas = 0.0;
    Double balanceIncial = 0.0;
    Double mercancia = 0.0;
    Double balance = 0.0;
    Double ventasNetas = 0.0;
    Double costoVenta = 0.0;
    Double utilidadBruta = 0.0;
    Double gastosOperaciones = 0.0;


    @GetMapping("/EstadoDeResultado")
    public String EstadoDeResultado(Model model, Integer dato) {
        List<RegistrosEstadosResultadoDAO> estado = new ArrayList<>();
        List<RegistrosEstadosResultado> registrosBase = new ArrayList<>();
        //-----------------------------------------------Areglar fecha-------------------------------------//

        registrosBase = this.estadoResultado.mostrar(2021);


        registrosBase.stream().forEach(c -> {
            if (c.getCatalogo().getCodigo().substring(0, 2).equals("51")) {
                RegistrosEstadosResultadoDAO registro = new RegistrosEstadosResultadoDAO(
                        //Integer id, Float total_cuentas, Float total_cuentas_tercer, Float total_cuentas_segundo, Catalogo catalogo, CicloContable cicloContable
                        c.getId(),
                        ((c.getTotal_cuentas() > 0) ? String.valueOf(c.getTotal_cuentas()) : ""),
                        ((c.getTotal_cuentas_tercer() > 0) ? String.valueOf(c.getTotal_cuentas_tercer()) : ""),
                        ((c.getTotal_cuentas_segundo() > 0) ? String.valueOf(c.getTotal_cuentas_segundo()) : ""),
                        c.getCatalogo(),
                        c.getCicloContable());
                if (c.getCatalogo().getCodigo().equals("51")) {
                    c.getCatalogo().setNombre("VENTAS NETAS");
                    ventasNetas = Double.valueOf(c.getTotal_cuentas_segundo());
                }
                estado.add(registro);
            }

        });
        registrosBase.stream().forEach(c -> {
            if (c.getCatalogo().getCodigo().substring(0, 2).equals("41")) {
                RegistrosEstadosResultadoDAO registro = new RegistrosEstadosResultadoDAO(
                        //Integer id, Float total_cuentas, Float total_cuentas_tercer, Float total_cuentas_segundo, Catalogo catalogo, CicloContable cicloContable
                        c.getId(),
                        ((c.getTotal_cuentas() > 0) ? String.valueOf(c.getTotal_cuentas()) : ""),
                        ((c.getTotal_cuentas_tercer() > 0) ? String.valueOf(c.getTotal_cuentas_tercer()) : ""),
                        ((c.getTotal_cuentas_segundo() > 0) ? String.valueOf(c.getTotal_cuentas_segundo()) : ""),
                        c.getCatalogo(),
                        c.getCicloContable());
                if (c.getCatalogo().getCodigo().equals("41")) {
                    c.getCatalogo().setNombre("COMPRAS NETAS");
                    comprasNetas = Double.valueOf(c.getTotal_cuentas_segundo());

                }
                estado.add(registro);

            }


        });


        registrosPartidaRepImp.mostrarPartida(2021);
        //-----------------------------------------------Areglar fecha-------------------------------------//
        if (registrosPartidaRepImp.mostrarPartida(2021).size() == 0) {
            //Retornar un mensaje
        } else {
            //Float total_cuentas_segundo, Catalogo catalogo, CicloContable cicloContable

            if (registrosPartidaRepImp.mostrarPartida(2021).size() >= 1) {
                RegistrosEstadosResultadoDAO registro = new RegistrosEstadosResultadoDAO(String.valueOf(registrosPartidaRepImp.mostrarPartida(2021).get(1).getHaber()), "0", registrosPartidaRepImp.mostrarPartida(2021).get(1).getCatalogo(), this.estadoResultado.mostrar(dato).get(1).getCicloContable());
                registro.setTotal_cuentas_segundo("");
                estado.add(registro);
                balanceIncial = Double.valueOf(registro.getTotal_cuentas_tercer());

                RegistrosEstadosResultadoDAO registro1 = new RegistrosEstadosResultadoDAO(this.estadoResultado.mostrar(2021).get(0).getCatalogo(), this.estadoResultado.mostrar(dato).get(0).getCicloContable());
                // registro1.setCatalogo(this.estadoResultado.mostrar(2021).get(0).getCatalogo());
                //registro.getCatalogo().setCodigo("0");
                registro1.getCatalogo().setNombre("MERCADERIA DISPONIBLE PARA LA VENTA");
                registro1.setTotal_cuentas_segundo(String.valueOf(Precision.round(comprasNetas + balanceIncial,2)));
                mercancia = Double.valueOf(registro1.getTotal_cuentas_segundo());
                estado.add(registro1);

                registro = new RegistrosEstadosResultadoDAO(String.valueOf(registrosPartidaRepImp.mostrarPartida(2021).get(0).getHaber()), "0", registrosPartidaRepImp.mostrarPartida(2021).get(0).getCatalogo(), this.estadoResultado.mostrar(dato).get(0).getCicloContable());
                registro.setId(1);
                registro.setTotal_cuentas_segundo("");

                registro.getCatalogo().setId(1);
                balance = Double.valueOf(registro.getTotal_cuentas_tercer());
                estado.add(registro);


                registro = new RegistrosEstadosResultadoDAO(catalogoRepositoryInt.buscarCodigo("3"));
                registro.getCatalogo().setNombre("COSTO DE VENTA");
                registro.setTotal_cuentas_segundo(String.valueOf(Precision.round(mercancia - balance,2)));
                costoVenta = Double.valueOf(registro.getTotal_cuentas_segundo());
                estado.add(registro);

                registro = new RegistrosEstadosResultadoDAO(catalogoRepositoryInt.buscarCodigo("21"));
                registro.getCatalogo().setNombre("UTILIDAD BRUTA");
                registro.setTotal_cuentas_segundo(String.valueOf(Precision.round(ventasNetas - costoVenta,2)));
                utilidadBruta = Double.valueOf(registro.getTotal_cuentas_segundo());
                estado.add(registro);


            } else {
                //mensaje debe haber un balance inicial y un final
            }


        }


        registrosBase.stream().forEach(c -> {
            if (c.getCatalogo().getCodigo().substring(0, 2).equals("42") || c.getCatalogo().getCodigo().substring(0, 2).equals("43")) {
                RegistrosEstadosResultadoDAO registro = new RegistrosEstadosResultadoDAO(
                        //Integer id, Float total_cuentas, Float total_cuentas_tercer, Float total_cuentas_segundo, Catalogo catalogo, CicloContable cicloContable
                        c.getId(),
                        ((c.getTotal_cuentas() > 0) ? String.valueOf(c.getTotal_cuentas()) : ""),
                        ((c.getTotal_cuentas_tercer() > 0) ? String.valueOf(c.getTotal_cuentas_tercer()) : ""),
                        ((c.getTotal_cuentas_segundo() > 0) ? String.valueOf(c.getTotal_cuentas_segundo()) : ""),
                        c.getCatalogo(),
                        c.getCicloContable());
                if (c.getCatalogo().getCodigo().substring(0, 2).equals("42")) {
                    gastosOperaciones = Double.valueOf(Precision.round(c.getTotal_cuentas_segundo(),2));
                }
                estado.add(registro);

            }

        });

        RegistrosEstadosResultadoDAO registro = new RegistrosEstadosResultadoDAO(catalogoRepositoryInt.buscarCodigo("6"));
        registro.getCatalogo().setNombre("UTILIDAD OPERACIÓN");
        registro.setTotal_cuentas_segundo(String.valueOf(Precision.round(utilidadBruta - gastosOperaciones,2)));
        Double utilidadoperciones = Double.valueOf(registro.getTotal_cuentas_segundo());
        estado.add(registro);

         registro = new RegistrosEstadosResultadoDAO(catalogoRepositoryInt.buscarCodigo("22"));
        registro.getCatalogo().setNombre("RESERVA LEGAL");
        registro.setTotal_cuentas_segundo(String.valueOf(Precision.round((utilidadoperciones * 0.07),2)));
        estado.add(registro);

        registro = new RegistrosEstadosResultadoDAO(catalogoRepositoryInt.buscarCodigo("2"));
        registro.getCatalogo().setNombre("UTILIDAD ANTES DE IMPUESTOS");
        registro.setTotal_cuentas_segundo(String.valueOf(Precision.round(((utilidadoperciones-utilidadoperciones * 0.07)),2)));
        Double utilididadImpuesto = Double.valueOf(registro.getTotal_cuentas_segundo());
        estado.add(registro);

        registro = new RegistrosEstadosResultadoDAO(catalogoRepositoryInt.buscarCodigo("3"));
        registro.getCatalogo().setNombre("IMPUESTO SOBRE LA RENTA");
        registro.setTotal_cuentas_segundo(String.valueOf(Precision.round(((utilididadImpuesto * 0.25)),2)));
        estado.add(registro);

        registro = new RegistrosEstadosResultadoDAO(catalogoRepositoryInt.buscarCodigo("4"));
        registro.getCatalogo().setNombre("UTILIDAD DEL EJERCICIO");
        registro.setTotal_cuentas_segundo(String.valueOf(Precision.round((utilididadImpuesto-(utilididadImpuesto * 0.25)),2)));
        estado.add(registro);

        model.addAttribute("tituloDeLaPagina", "Estado de Resultado");
        model.addAttribute("estados", estado);
        model.addAttribute("anioActual", registrosBase.get(0).getCicloContable().getFecha_fin());
        model.addAttribute("anios", cicloContableReposytory.findAll());
        return "EstadoDeResultado";
    }
}
