package com.SistemaContable.servicio.JavierAyala.Interfaces;

import com.SistemaContable.model.JavierAyala.Catalogo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CatalogoServicesInterfaces {


    //mostrar los registros segun busqueda por dato
    public List<Catalogo> buscar(String buscar);

    //mostra los registros según busqueda por dato y valor
    public String buscar(String dato,String buscar);

    //Guardar una nueva cuenta de catálogo
    public String save(Catalogo catalogo);

    //Elimina una cuenta de catálogo por id
    public String delete(Catalogo id);

    //Trae el numero de registros que tiene el catálogo segun el dato like
    public int searchLike(String buscar);

    //Validar el codigo entrante para poder ser guardado.
    public Boolean validarCodigo(String codigo);
}
