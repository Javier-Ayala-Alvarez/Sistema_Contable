package com.SistemaContable.Repository.DiegoMejia.dao.Api;

import com.SistemaContable.model.DiegoMejia.Partida;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PartidaDaoApi extends CrudRepository <Partida, Long> {


}
