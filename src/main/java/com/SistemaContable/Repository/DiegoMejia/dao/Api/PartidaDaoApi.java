package com.SistemaContable.Repository.DiegoMejia.dao.Api;

import com.SistemaContable.model.DiegoMejia.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface PartidaDaoApi extends JpaRepository<Partida, Long> {

    @Query(value = "select id from partida order by id desc limit 1",nativeQuery = true)
    Long getLastId();


    Collection<Partida> findAllByActivoIsTrue();

}
