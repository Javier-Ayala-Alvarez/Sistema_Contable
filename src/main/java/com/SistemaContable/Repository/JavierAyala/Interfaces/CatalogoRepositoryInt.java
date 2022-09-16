package com.SistemaContable.Repository.JavierAyala.Interfaces;

import com.SistemaContable.model.JavierAyala.Catalogo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CatalogoRepositoryInt extends JpaRepository<Catalogo, Integer> {


    @Query("SELECT c FROM Catalogo c WHERE concat(c.nombre,c.codigo, c.saldoCuenta, c.tipoCuenta) like %?1%")
    public Page<Catalogo> mostrarCatalogo(String frase, Pageable pageable);

    @Query("select c from Catalogo c where c.codigo = ?1")
    public Catalogo buscarCodigo(String codigo);
}
