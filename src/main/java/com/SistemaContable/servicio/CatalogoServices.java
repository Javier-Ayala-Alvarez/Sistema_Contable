package com.SistemaContable.servicio;

import com.SistemaContable.Repository.CatalogoRepositoryInt;
import com.SistemaContable.Repository.CatalogoRespository;
import com.SistemaContable.model.Catalogo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CatalogoServices   {
    @Autowired
    CatalogoRespository catalogoRespository;
    @Autowired
    CatalogoRepositoryInt catalogoRepositoryInt;

    public Page<Catalogo> mostrarCatalogo(Pageable pageable) {
        return catalogoRepositoryInt.findAll(pageable);
        //Sort.by("codigo").ascending(),
    }
    public List<Catalogo> buscar(String buscar) {
        return catalogoRespository.buscar(buscar);
    }
    public String buscar(String dato,String buscar) {
        return catalogoRespository.buscar(dato, buscar);
    }

    public String save(Catalogo catalogo){
         catalogoRepositoryInt.save(catalogo);
         return "0";
    }

    public String delete(Catalogo id){
        catalogoRepositoryInt.delete(id);
        return "true";
    }
    public int searchLike(String buscar){
        return catalogoRespository.searchLike(buscar);
    }

}

