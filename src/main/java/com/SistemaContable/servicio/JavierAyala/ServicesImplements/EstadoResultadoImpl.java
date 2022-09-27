package com.SistemaContable.servicio.JavierAyala.ServicesImplements;

import com.SistemaContable.Repository.JavierAyala.Interfaces.EstadoResultadoInt;
import com.SistemaContable.model.JavierAyala.RegistrosEstadosResultado;
import com.SistemaContable.servicio.JavierAyala.Interfaces.EstadoResultadoInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EstadoResultadoImpl implements EstadoResultadoInter {
    @Autowired
    EstadoResultadoInt estadoResultadoInt;
    @Override
    public List<RegistrosEstadosResultado> mostrar() {
        return estadoResultadoInt.mostrar();
    }
}
