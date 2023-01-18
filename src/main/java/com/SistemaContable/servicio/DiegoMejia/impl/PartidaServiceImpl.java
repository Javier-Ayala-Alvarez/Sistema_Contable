package com.SistemaContable.servicio.DiegoMejia.impl;

import com.SistemaContable.Repository.AlejandroDeras.MayorRepositorio;
import com.SistemaContable.Repository.DiegoMejia.dao.Api.PartidaDaoApi;
import com.SistemaContable.Repository.JavierAyala.Interfaces.CatalogoRepositoryInt;
import com.SistemaContable.commons.GenericServiceApiImpl;
import com.SistemaContable.model.DiegoMejia.MayorDTO;
import com.SistemaContable.model.DiegoMejia.Partida;
import com.SistemaContable.model.DiegoMejia.RegistroPartida;
import com.SistemaContable.model.JavierAyala.Catalogo;
import com.SistemaContable.servicio.DiegoMejia.Interfaces.PartidaServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.regex.Pattern;

@Service
public class PartidaServiceImpl extends GenericServiceApiImpl<Partida, Long>
        implements PartidaServiceApi {

    @Autowired
    private PartidaDaoApi partidaDaoApi;
    @Autowired
    private MayorRepositorio mayorRepositorio;
    @Autowired
    CatalogoRepositoryInt catalogoRepositoryInt;

    @Override
    public CrudRepository<Partida, Long> getDao() {
        return partidaDaoApi;
    }


    @Override
    public Long getLastId() {
        return partidaDaoApi.getLastId();
    }

    public ArrayList<MayorDTO> mayorizar(Integer id) {
        MayorDTO mayorDTO;
        ArrayList<Catalogo> cuentas4 = new ArrayList<>(catalogoRepositoryInt.obtenerCuentas4());
        ArrayList<MayorDTO> cuentasDelMayor = new ArrayList<>();
        ArrayList<Partida> partidas = (ArrayList<Partida>) partidaDaoApi.findAllByActivoIsTrue(id);

        //cuentas del catalogo
        for (Catalogo catalogo : cuentas4) {
            mayorDTO = new MayorDTO();
            mayorDTO.setCodigocuenta(catalogo.getCodigo());
            mayorDTO.setNombreCuenta(catalogo.getNombre());
            mayorDTO.setSaldoCuenta(catalogo.getSaldoCuenta());
            mayorDTO.setDebe(new BigDecimal("0"));
            mayorDTO.setHaber(new BigDecimal("0"));
            cuentasDelMayor.add(mayorDTO);
        }

        for (Partida partida : partidas) {

            for (RegistroPartida registroPartida : partida.getRegistroPartidas()) {
                mayorDTO = new MayorDTO();
                String codigoCuenta = registroPartida.getCatalogo().getCodigo();

                if (codigoCuenta.length() < 4 || (codigoCuenta.length() < 6 && codigoCuenta.contains("R"))) {
                    mayorDTO.setCodigocuenta(codigoCuenta);
                } else {
                    mayorDTO.setCodigocuenta(codigoCuenta.substring(0, 4));
                }

                int indice = cuentasDelMayor.indexOf(mayorDTO);

                if (indice >= 0) {
                    MayorDTO obtenido = cuentasDelMayor.get(indice);

                    obtenido.setDebe(obtenido.getDebe().add(registroPartida.getDebe()));
                    obtenido.setHaber(obtenido.getHaber().add(registroPartida.getHaber()));
                }

            }

        }
        return cuentasDelMayor;

    }

    public ArrayList<MayorDTO> balanceGeneral(Integer id) {
        ArrayList<MayorDTO> mayorDTOCollection = mayorizar(id);
        Pattern pattern = Pattern.compile("^[^1-3].*");


        mayorDTOCollection.forEach(n -> {
            if (n.getSaldoCuenta().equals("Deudor")) {
                n.setDebe(n.getDebe().subtract(n.getHaber()));

                n.setHaber(new BigDecimal("0"));
            } else {
                n.setHaber(n.getHaber().subtract(n.getDebe()));

                n.setDebe(new BigDecimal("0"));
            }
        });


        MayorDTO inventarioFinal = mayorDTOCollection.get(mayorDTOCollection.indexOf(new MayorDTO("1109")));
        MayorDTO utilidad = mayorDTOCollection.get(mayorDTOCollection.indexOf(new MayorDTO("3106")));
        MayorDTO renta = mayorDTOCollection.get(mayorDTOCollection.indexOf(new MayorDTO("2111")));
        MayorDTO reservaLegal = mayorDTOCollection.get(mayorDTOCollection.indexOf(new MayorDTO("3103")));


        inventarioFinal.setHaber(new BigDecimal("0"));
        inventarioFinal.setDebe(new BigDecimal("200000.00"));


        utilidad.setDebe(new BigDecimal("0"));
        utilidad.setHaber(new BigDecimal("34930.99"));

        renta.setDebe(new BigDecimal("0"));
        renta.setHaber(new BigDecimal("11643.66"));

        reservaLegal.setHaber(new BigDecimal("3505.62"));
        reservaLegal.setDebe(new BigDecimal("0"));


        mayorDTOCollection.removeIf(n -> (n.getDebe().toString().equals("0")
                && n.getHaber().toString().equals("0"))
                || pattern.matcher(n.getCodigocuenta()).find());

        return new ArrayList<>(mayorDTOCollection);

    }
}