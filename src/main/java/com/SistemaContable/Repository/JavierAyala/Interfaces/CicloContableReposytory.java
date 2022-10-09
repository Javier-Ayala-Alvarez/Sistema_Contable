package com.SistemaContable.Repository.JavierAyala.Interfaces;

import com.SistemaContable.model.DiegoMejia.Partida;
import com.SistemaContable.model.JavierAyala.CicloContable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CicloContableReposytory extends JpaRepository<CicloContable, Integer> {

}
