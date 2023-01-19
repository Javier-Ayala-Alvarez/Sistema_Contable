package com.SistemaContable.Repository.JavierAyala.Interfaces;

import com.SistemaContable.model.DiegoMejia.Partida;
import com.SistemaContable.model.JavierAyala.CicloContable;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CicloContableReposytory extends JpaRepository<CicloContable, Integer> {
    @Query("select  EXTRACT(YEAR from r.fecha_fin) from CicloContable r order by r.fecha_fin desc ")
    public int[] anioactual();



    @Query("select max(r.id) from CicloContable r")
    public int MaxId();

    @Query("select r.id from CicloContable r where EXTRACT(YEAR from r.fecha_fin) =:anio ")
    public Integer getAnioactual(@Param("anio") Integer anio);


    public Optional<CicloContable> findFirstByOrderByIdDesc();
}
