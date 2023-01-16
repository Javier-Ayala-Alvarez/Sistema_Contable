package com.SistemaContable.Repository.Gerson;

import com.SistemaContable.model.Gerson.DataEmpresa;
import com.SistemaContable.model.JavierAyala.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataEmpresaRepositorio extends JpaRepository<Empresa, Long> {

}
