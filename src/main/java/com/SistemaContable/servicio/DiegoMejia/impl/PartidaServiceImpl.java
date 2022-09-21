package com.SistemaContable.servicio.DiegoMejia.impl;

import com.SistemaContable.Repository.DiegoMejia.dao.Api.PartidaDaoApi;
import com.SistemaContable.commons.GenericServiceApiImpl;
import com.SistemaContable.model.DiegoMejia.Partida;
import com.SistemaContable.servicio.DiegoMejia.Interfaces.PartidaServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public class PartidaServiceImpl extends GenericServiceApiImpl<Partida,Long>
        implements PartidaServiceApi {

    @Autowired
    private PartidaDaoApi partidaDaoApi;

    @Override
    public CrudRepository <Partida,Long> getDao() {
        return partidaDaoApi;
    }
}
