package com.SistemaContable.Repository.DiegoMejia;

import com.SistemaContable.model.JavierAyala.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
    @Query("select r from Empresa r order by r.id desc limit 1")
    Empresa obtenerUltimaEmpresa();
}
