package com.SistemaContable.servicio.JavierAyala.Interfaces;

import com.SistemaContable.model.JavierAyala.RegistrosEstadosResultado;
import com.SistemaContable.model.JavierAyala.RegistrosEstadosResultadoDAO;

import java.util.ArrayList;
import java.util.List;

public interface EstadoResultadoInter {
    public List<RegistrosEstadosResultado> mostrar(Integer dato);
}
