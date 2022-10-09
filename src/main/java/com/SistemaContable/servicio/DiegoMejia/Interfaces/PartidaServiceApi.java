package com.SistemaContable.servicio.DiegoMejia.Interfaces;

import com.SistemaContable.commons.GenericServiceApi;
import com.SistemaContable.model.DiegoMejia.Partida;
import org.springframework.data.jpa.repository.Query;

public interface PartidaServiceApi extends GenericServiceApi <Partida,Long> {


    Long getLastId();

}
