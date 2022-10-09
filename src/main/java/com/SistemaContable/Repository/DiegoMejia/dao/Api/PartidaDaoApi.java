package com.SistemaContable.Repository.DiegoMejia.dao.Api;

import com.SistemaContable.model.DiegoMejia.Partida;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PartidaDaoApi extends CrudRepository <Partida, Long> {

<<<<<<< HEAD

=======
    @Query(value = "select id from partida order by id desc limit 1",nativeQuery = true)
    Long getLastId();
>>>>>>> 87f530c3262d83770dd33954f07d40ff90a65209
}
