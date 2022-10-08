package com.SistemaContable.Repository.DiegoMejia.dao.Api;

import com.SistemaContable.model.DiegoMejia.Partida;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PartidaDaoApi extends CrudRepository <Partida, Long> {

    @Query(value = "select id from partida order by id desc limit 1",nativeQuery = true)
    Long getLastId();
}
