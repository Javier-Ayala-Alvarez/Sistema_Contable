package com.SistemaContable.servicio.DiegoMejia.Interfaces;

import com.SistemaContable.commons.GenericServiceApi;
import com.SistemaContable.model.DiegoMejia.MayorDTO;
import com.SistemaContable.model.DiegoMejia.Partida;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface PartidaServiceApi extends GenericServiceApi <Partida,Long> {
    public ArrayList<MayorDTO> mayorizar();

    Long getLastId();

}
