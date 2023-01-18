package com.SistemaContable.servicio.Gerson;

import com.SistemaContable.commons.GenericServiceApi;
import com.SistemaContable.model.DiegoMejia.MayorDTO;
import com.SistemaContable.model.DiegoMejia.Partida;

import java.util.ArrayList;
import java.util.List;

public interface BalanzaDeComprobacionInterfaz extends GenericServiceApi<Partida,Long> {
    public ArrayList<MayorDTO> mayorizar();

    Long getLastId();
    }
