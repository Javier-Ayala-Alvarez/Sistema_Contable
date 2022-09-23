package com.SistemaContable.Repository.JavierAyala.Interfaces;

import com.SistemaContable.model.JavierAyala.Catalogo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CatalogoRepositoryInt extends JpaRepository<Catalogo, Integer> {


    @Query("SELECT c FROM Catalogo c WHERE concat(c.nombre,c.codigo, c.saldoCuenta, c.tipoCuenta) like %?1%")
    public Page<Catalogo> mostrarCatalogo(String frase, Pageable pageable);

    @Query("SELECT c FROM Catalogo c WHERE LENGTH(c.codigo)=4 or LENGTH(c.codigo)=5 ORDER BY c.codigo ASC")
    public List<Catalogo> mostrarManual();

    @Query("select c from Catalogo c where c.codigo = ?1")
    public Catalogo buscarCodigo(String codigo);
}
