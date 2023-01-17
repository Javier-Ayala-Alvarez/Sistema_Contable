package com.SistemaContable.Repository.DiegoMejia;

import com.SistemaContable.model.DiegoMejia.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PartidaRepository  extends JpaRepository<Partida, Long> {

@Query("select max(r.id) from Partida r")
    public int idMax();



    @Query("select r from Partida r where r.id = ?1")
    public Partida Buscar(Integer id);
}
