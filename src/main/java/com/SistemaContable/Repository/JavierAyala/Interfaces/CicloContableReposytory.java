package com.SistemaContable.Repository.JavierAyala.Interfaces;

import com.SistemaContable.model.DiegoMejia.Partida;
import com.SistemaContable.model.JavierAyala.CicloContable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CicloContableReposytory extends JpaRepository<CicloContable, Integer> {
    @Query("select  EXTRACT(YEAR from r.fecha_fin) from CicloContable r ")
    public int[] anioactual();

    @Query("select max(r.id) from CicloContable r")
    public int MaxId();


    public Optional<CicloContable> findFirstByOrderByIdDesc();
}
