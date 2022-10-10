package com.SistemaContable.Repository.DiegoMejia;

import com.SistemaContable.model.DiegoMejia.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PartidaRepository  extends JpaRepository<Partida, Long> {



}
