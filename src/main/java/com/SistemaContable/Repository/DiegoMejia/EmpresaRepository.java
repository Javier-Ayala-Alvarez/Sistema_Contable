package com.SistemaContable.Repository.DiegoMejia;

import com.SistemaContable.model.JavierAyala.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

    Empresa findFirstByOrderByIdDesc();
}
