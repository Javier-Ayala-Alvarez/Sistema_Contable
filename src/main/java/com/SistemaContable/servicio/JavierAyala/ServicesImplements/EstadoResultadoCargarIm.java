package com.SistemaContable.servicio.JavierAyala.ServicesImplements;

import com.SistemaContable.Repository.DiegoMejia.PartidaRepository;
import com.SistemaContable.Repository.Gerson.DataEmpresaRepositorio;
import com.SistemaContable.Repository.JavierAyala.Interfaces.CatalogoRepositoryInt;
import com.SistemaContable.Repository.JavierAyala.Interfaces.CicloContableReposytory;
import com.SistemaContable.Repository.JavierAyala.Interfaces.EstadoResultadoPercistencia;
import com.SistemaContable.Repository.JavierAyala.Interfaces.RegistrosPartidaRepImp;
import com.SistemaContable.model.DiegoMejia.Partida;
import com.SistemaContable.model.DiegoMejia.RegistroPartida;
import com.SistemaContable.model.JavierAyala.CicloContable;
import com.SistemaContable.model.JavierAyala.EstadoResultado;
import com.SistemaContable.model.JavierAyala.RegistrosEstadosResultado;
import com.SistemaContable.model.JavierAyala.RegistrosEstadosResultadoDAO;
import com.SistemaContable.servicio.JavierAyala.Interfaces.EstadoResultadoCargarIn;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class EstadoResultadoCargarIm implements EstadoResultadoCargarIn {
    @Autowired
    DataEmpresaRepositorio dataEmpresaRepositorio;

    @Autowired
    EstadoResultadoImpl estadoResultado;
    @Autowired
    CatalogoRepositoryInt catalogoRepositoryInt;
    @Autowired
    CicloContableReposytory cicloContableReposytory;

    @Autowired
    EstadoResultadoPercistencia estadoResultadoPercistencia;
    @Autowired
    RegistrosPartidaRepImp registrosPartidaRepImp;

    @Autowired
    PartidaRepository partidaRepository;
    Double comprasNetas = 0.0;
    Double balanceIncial = 0.0;
    Double mercancia = 0.0;
    Double balance = 0.0;
    Double ventasNetas = 0.0;
    Double costoVenta = 0.0;
    Double utilidadBruta = 0.0;
    Double gastosOperaciones = 0.0;

    @Override
    public void cargaDatosEstado(Integer anio, String balanceafinal) {
        List<RegistrosEstadosResultadoDAO> estado = new ArrayList<>();
        List<RegistrosEstadosResultado> registrosBase = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yy");


        //-----------------------------------------------Areglar fecha-------------------------------------//
        estadoResultadoPercistencia.deleteAll();//Elimina los arcivos que ya estan.
        registrosBase = this.estadoResultado.mostrar(anio);
        registrosBase.stream().forEach(c -> {
            if (c.getCatalogo().getCodigo().substring(0, 2).equals("51")) {
                EstadoResultado registro = new EstadoResultado(

                        c.getCatalogo().getNombre(),
                        ((c.getTotal_cuentas() > 0) ? String.valueOf(c.getTotal_cuentas()) : ""),
                        ((c.getTotal_cuentas_tercer() > 0) ? String.valueOf(c.getTotal_cuentas_tercer()) : ""),
                        ((c.getTotal_cuentas_segundo() > 0) ? String.valueOf(c.getTotal_cuentas_segundo()) : ""),
                        String.valueOf(anio));
                if (c.getCatalogo().getCodigo().equals("51")) {
                    c.getCatalogo().setNombre("VENTAS NETAS");
                    ventasNetas = Double.valueOf(c.getTotal_cuentas_segundo());
                }
                estadoResultadoPercistencia.save(registro);
            }


        });
        registrosBase.stream().forEach(c -> {

            if (c.getCatalogo().getCodigo().substring(0, 2).equals("41")) {
                EstadoResultado registro = new EstadoResultado(

                        c.getCatalogo().getNombre(),
                        ((c.getTotal_cuentas() > 0) ? String.valueOf(c.getTotal_cuentas()) : ""),
                        ((c.getTotal_cuentas_tercer() > 0) ? String.valueOf(c.getTotal_cuentas_tercer()) : ""),
                        ((c.getTotal_cuentas_segundo() > 0) ? String.valueOf(c.getTotal_cuentas_segundo()) : ""),
                        String.valueOf(anio));
                if (c.getCatalogo().getCodigo().equals("41")) {
                    c.getCatalogo().setNombre("COMPRAS NETAS");
                    comprasNetas = Double.valueOf(c.getTotal_cuentas_segundo());
                }
                estadoResultadoPercistencia.save(registro);
            }

        });
        registrosPartidaRepImp.mostrarPartida(anio);
        //-----------------------------------------------Areglar fecha-------------------------------------//
        if (registrosPartidaRepImp.mostrarPartida(anio).size() == 0) {
            //Retornar un mensaje
        } else {
            //Float total_cuentas_segundo, Catalogo catalogo, CicloContable cicloContable
            String anioanterior = String.valueOf(anio - 1);
            RegistroPartida registroPartida = new RegistroPartida();

            if (registrosPartidaRepImp.mostrarPartida(Integer.parseInt(anioanterior)).size() == 0) {

                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                int idmax = 0;
                try {
                    CicloContable ciclo = new CicloContable();
                    try {
                        idmax = (cicloContableReposytory.MaxId() + 1);
                    } catch (Exception e) {
                        idmax = 1;
                    }
                    Date date = format1.parse((anioanterior) + "-01-01");//(anio-1)+"-01-01"
                    ciclo.setFecha_fin(date);
                    ciclo.setId(idmax);
                    ciclo.setTotal(0.0);
                    cicloContableReposytory.save(ciclo);
                    ciclo.setId(cicloContableReposytory.MaxId());

                    Partida partida = new Partida();
                    int idpartida = 1;
                    try {
                        idpartida = partidaRepository.idMax() + 1;
                    } catch (Exception e) {
                        idpartida = 1;
                    }
                    partida.setId(Long.valueOf(idpartida));
                    partida.setActivo(true);
                    partida.setDescripcion("Cierre de ciclo");
                    partida.setFecha(date);
                    partida.setCicloContable(ciclo);
                    partidaRepository.save(partida);

                    int idpartidaregist = 1;
                    try {
                        idpartidaregist = registrosPartidaRepImp.idMax() + 1;
                    } catch (Exception e) {
                        idpartidaregist = 1;
                    }

                    registroPartida.setId(Long.valueOf(idpartidaregist));
                    registroPartida.setPartida(partida);
                    BigDecimal sDebe = new BigDecimal(balance);
                    registroPartida.setDebe(sDebe);
                    registroPartida.setHaber(sDebe);
                    registroPartida.setCatalogo(catalogoRepositoryInt.buscarCodigo("1109"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (registrosPartidaRepImp.mostrarPartida(anio).size() >= 1) {
                EstadoResultado registro = new EstadoResultado("INVENTARIO INICIAL", "", String.valueOf(registrosPartidaRepImp.mostrarPartida(anio).get(0).getDebe()), "", String.valueOf(anio));
                estadoResultadoPercistencia.save(registro);
                balanceIncial = Double.valueOf(registro.getDato2());

                EstadoResultado registro1 = new EstadoResultado("MERCADERIA DISPONIBLE PARA LA VENTA", "", "", String.valueOf(Precision.round(comprasNetas + balanceIncial, 2)), String.valueOf(anio));
                mercancia = Double.valueOf(String.valueOf(Precision.round(comprasNetas + balanceIncial, 2)));
                estadoResultadoPercistencia.save(registro1);
                Double inventarioFinal = 0.00;
                if (balanceafinal.equals("")) {
                    inventarioFinal = Double.parseDouble(String.valueOf(registrosPartidaRepImp.mostrarPartida(anio).get(0).getDebe())) + comprasNetas - ventasNetas;
                    registroPartida.setDebe(BigDecimal.valueOf(inventarioFinal));
                }else {
                    registroPartida.setDebe(BigDecimal.valueOf(Double.parseDouble(balanceafinal)));
                }
                if(registroPartida.getPartida()== null){
                    registroPartida.setCatalogo(registrosPartidaRepImp.mostrarPartida(Integer.parseInt(anioanterior)).get(0).getCatalogo());
                    registroPartida.setId(registrosPartidaRepImp.mostrarPartida(Integer.parseInt(anioanterior)).get(0).getId());
                    registroPartida.setPartida(registrosPartidaRepImp.mostrarPartida(Integer.parseInt(anioanterior)).get(0).getPartida());
                    registroPartida.setHaber(BigDecimal.valueOf(0.0));
                }
                registrosPartidaRepImp.save(registroPartida);

                EstadoResultado registro2 = new EstadoResultado("INVENTARIO FINAL", "", (String.valueOf(registrosPartidaRepImp.mostrarPartida(anio).get(1).getDebe())), "", String.valueOf(anio));
                estadoResultadoPercistencia.save(registro2);
                balance = Double.valueOf(registro2.getDato2());

                EstadoResultado registro3 = new EstadoResultado("COSTO DE VENTA", "", "", String.valueOf(Precision.round(mercancia - balance, 2)), String.valueOf(anio));
                estadoResultadoPercistencia.save(registro3);
                costoVenta = Double.valueOf(String.valueOf(Precision.round(mercancia - balance, 2)));

                EstadoResultado registro4 = new EstadoResultado("UTILIDAD BRUTA", "", "", String.valueOf(Precision.round(ventasNetas - costoVenta, 2)), String.valueOf(anio));
                estadoResultadoPercistencia.save(registro4);
                utilidadBruta = Double.valueOf(String.valueOf(Precision.round(ventasNetas - costoVenta, 2)));


            }


        }
        registrosBase.stream().forEach(c -> {

            if (c.getCatalogo().getCodigo().substring(0, 2).equals("42") || c.getCatalogo().getCodigo().substring(0, 2).equals("43")) {
                EstadoResultado registro = new EstadoResultado(

                        c.getCatalogo().getNombre(),
                        ((c.getTotal_cuentas() > 0) ? String.valueOf(c.getTotal_cuentas()) : ""),
                        ((c.getTotal_cuentas_tercer() > 0) ? String.valueOf(c.getTotal_cuentas_tercer()) : ""),
                        ((c.getTotal_cuentas_segundo() > 0) ? String.valueOf(c.getTotal_cuentas_segundo()) : ""),
                        String.valueOf(anio));
                if (c.getCatalogo().getCodigo().equals("42")) {
                    gastosOperaciones = Double.valueOf(Precision.round(c.getTotal_cuentas_segundo(), 2));
                }
                estadoResultadoPercistencia.save(registro);
            }

        });
        if (this.estadoResultado.mostrar(anio) != null) {
            EstadoResultado registro4 = new EstadoResultado("UTILIDAD OPERACIÓN", "", "", String.valueOf(Precision.round(utilidadBruta - gastosOperaciones, 2)), String.valueOf(anio));
            estadoResultadoPercistencia.save(registro4);
            Double utilidadoperciones = Double.valueOf(String.valueOf(Precision.round(utilidadBruta - gastosOperaciones, 2)));

            EstadoResultado registro5 = new EstadoResultado("RESERVA LEGAL", "", "", String.valueOf(Precision.round((utilidadoperciones * 0.07), 2)), String.valueOf(anio));
            estadoResultadoPercistencia.save(registro5);

            EstadoResultado registro7 = new EstadoResultado("UTILIDAD ANTES DE IMPUESTOS", "", "", String.valueOf(Precision.round((utilidadoperciones - utilidadoperciones * 0.07), 2)), String.valueOf(anio));
            estadoResultadoPercistencia.save(registro7);
            Double utilididadImpuesto = Double.valueOf(String.valueOf(Precision.round((utilidadoperciones - utilidadoperciones * 0.07), 2)));
            //25000 ventastotales
            Double renta = 0.0;
            if (utilididadImpuesto <= 150000.00) {
                renta = 0.25;
            } else {
                renta = 0.30;
            }
            EstadoResultado registro8 = new EstadoResultado("IMPUESTO SOBRE LA RENTA", "", "", String.valueOf(Precision.round((utilididadImpuesto * renta), 2)), String.valueOf(anio));
            estadoResultadoPercistencia.save(registro8);

            EstadoResultado registro9 = new EstadoResultado("UTILIDAD DEL EJERCICIO", "", "", String.valueOf(Precision.round((utilididadImpuesto - (utilididadImpuesto * 0.25)), 2)), String.valueOf(anio));
            estadoResultadoPercistencia.save(registro9);
        }

    }

    public ResponseEntity<byte[]> exportReport() throws FileNotFoundException, JRException {
        try {
            List<EstadoResultado> registro = estadoResultadoPercistencia.findAll(); //= catalogoRepositoryInt.findAll(Sort.by("codigo").ascending());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(registro);
            final HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("NombreEmpresa", dataEmpresaRepositorio.findAll().get(0).getNombreEmpresa());
            JasperPrint empReport = JasperFillManager.fillReport(JasperCompileManager.compileReport(ResourceUtils.getFile("classpath:EstadoResultado_1.jrxml").getAbsolutePath()) // path of the jasper report
                    , parameters //empParams dynamic parameters
                    , dataSource);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);


            headers.setContentDispositionFormData("filename", "EstadoResultado.pdf");


            return new ResponseEntity<byte[]>(JasperExportManager.exportReportToPdf(empReport), headers, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
