package com.SistemaContable.Repository.JavierAyala.Interfaces;

import com.SistemaContable.model.JavierAyala.EstadoResultado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoResultadoPercistencia extends JpaRepository<EstadoResultado, Integer> {
    @Query("select r from EstadoResultado r")
    public List<EstadoResultado> findAll();
}
