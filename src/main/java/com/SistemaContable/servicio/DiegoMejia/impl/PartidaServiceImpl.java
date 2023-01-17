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

    ArrayList<MayorDTO> Mayorizar() {
        MayorDTO mayorDTO;
        ArrayList<Catalogo> cuentas4 = new ArrayList<>(catalogoRepositoryInt.obtenerCuentas4());
        ArrayList<MayorDTO> cuentasDelMayor = new ArrayList<>();
        ArrayList<Partida> partidas = (ArrayList<Partida>) partidaDaoApi.findAllByActivoIsTrue();

        //cuentas del catalogo
        for (Catalogo catalogo : cuentas4) {
            mayorDTO = new MayorDTO();
            mayorDTO.setCodigocuenta(catalogo.getCodigo());
            mayorDTO.setDebe(new BigDecimal("0"));
            mayorDTO.setHaber(new BigDecimal("0"));
            cuentasDelMayor.add(mayorDTO);
        }

        for (Partida partida : partidas) {

            for (RegistroPartida registroPartida : partida.getRegistroPartidas()) {
                mayorDTO = new MayorDTO();
                mayorDTO.setCodigocuenta(registroPartida.getCatalogo().getCodigo().substring(0, 4));

                int indice = cuentasDelMayor.indexOf(mayorDTO);

                MayorDTO obtenido = cuentasDelMayor.get(indice);

                obtenido.getDebe().add(registroPartida.getDebe());
                obtenido.getHaber().add(registroPartida.getHaber());

            }

        }
        return cuentasDelMayor;

    }
}