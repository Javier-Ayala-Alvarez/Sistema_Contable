package com.SistemaContable.Repository.JavierAyala.Interfaces;

import com.SistemaContable.model.DiegoMejia.Partida;
import com.SistemaContable.model.DiegoMejia.RegistroPartida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegistrosPartidaRepImp extends JpaRepository<RegistroPartida, Long> {

    //Metodo para sacar el balance
    @Query("SELECT r FROM RegistroPartida r where r.catalogo.codigo = '1109' AND EXTRACT(YEAR from r.partida.cicloContable.fecha_fin) <= ?1 order by r.partida.cicloContable.fecha_fin desc ")
    public List<RegistroPartida> mostrarPartida(Integer anio);

    @Query("select count(r) from RegistroPartida r where r.catalogo.id = ?1")
    public int numeroRegistrosSaldo(Integer id);

}

