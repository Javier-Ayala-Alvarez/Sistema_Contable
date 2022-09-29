package com.SistemaContable.Repository.JavierAyala.Interfaces;

import com.SistemaContable.model.JavierAyala.RegistrosEstadosResultado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EstadoResultadoInt extends JpaRepository<RegistrosEstadosResultado, Integer> {

    @Query(value = "SELECT r FROM  RegistrosEstadosResultado r  WHERE r.cicloContable.id = 2 order by r.catalogo.codigo asc ")
    public List<RegistrosEstadosResultado> mostrar();
}
