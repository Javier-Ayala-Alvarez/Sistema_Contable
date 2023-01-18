package com.SistemaContable.Repository.DiegoMejia.dao.Api;

import com.SistemaContable.model.DiegoMejia.Partida;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface PartidaDaoApi extends JpaRepository<Partida, Long> {

    @Query(value = "select id from partida order by id desc limit 1",nativeQuery = true)
    Long getLastId();


    @Query("select p from Partida p where p.activo = true and p.cicloContable.id =:id")
    Collection<Partida> findAllByActivoIsTrue(@Param("id")Integer id);

}
