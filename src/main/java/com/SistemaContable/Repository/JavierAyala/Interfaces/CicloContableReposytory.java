package com.SistemaContable.Repository.JavierAyala.Interfaces;

import com.SistemaContable.model.DiegoMejia.Partida;
import com.SistemaContable.model.JavierAyala.CicloContable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CicloContableReposytory extends JpaRepository<CicloContable, Integer> {
    @Query("select  EXTRACT(YEAR from r.fecha_fin) from CicloContable r ")
    public int[] anioactual();

    @Query("select max(r.id) from CicloContable r")
    public int MaxId();

    @Query("select r from CicloContable r where r.estado = true fetch first 1 rows only")
    public CicloContable cicloactivo();
}
