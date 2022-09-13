package com.SistemaContable.Repository.JavierAyala.Interfaces;

import com.SistemaContable.model.JavierAyala.Catalogo;

import java.util.List;

public interface CatalogoRepositoryInterface {
    //mostrar todos los registros de catalogo
    public List<Catalogo> mostrarCatalogo();

    //mostrar registros por busqueda
    public List<Catalogo> buscar(String buscar);

    //mostrar registros por busqueda de valor y dato
    public String buscar(String valor, String buscar);

    //Mostrar el numero de registros segun la busqueda de like
    public int searchLike(String buscar);
}
