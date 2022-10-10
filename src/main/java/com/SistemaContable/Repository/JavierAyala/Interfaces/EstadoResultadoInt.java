package com.SistemaContable.Repository.JavierAyala.Interfaces;

import com.SistemaContable.model.JavierAyala.RegistrosEstadosResultado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EstadoResultadoInt extends JpaRepository<RegistrosEstadosResultado, Integer> {
//error de datos
    @Query(value = "SELECT nullif(r, '0')  FROM  RegistrosEstadosResultado r  WHERE EXTRACT(YEAR from r.cicloContable.fecha_fin) = ?1 order by r.catalogo.codigo asc ")
    public List<RegistrosEstadosResultado> mostrar(Integer dato);
    @Query(value = "SELECT nullif(r, '0') FROM  RegistrosEstadosResultado r   order by r.catalogo.codigo asc  ")
    public RegistrosEstadosResultado mostrarId( String id);


}
