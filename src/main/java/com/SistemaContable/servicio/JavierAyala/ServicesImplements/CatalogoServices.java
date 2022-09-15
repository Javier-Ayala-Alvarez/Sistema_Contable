package com.SistemaContable.servicio.JavierAyala.ServicesImplements;

import com.SistemaContable.Repository.JavierAyala.Interfaces.CatalogoRepositoryInt;
import com.SistemaContable.Repository.JavierAyala.Interfaces.CatalogoRepositoryInterface;
import com.SistemaContable.model.JavierAyala.Catalogo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CatalogoServices  implements CatalogoRepositoryInterface {

    @Autowired

    CatalogoRepositoryInterface catalogoRespositoryInterface;
    @Autowired
    CatalogoRepositoryInt catalogoRepositoryInt;


    public Page<Catalogo> mostrarCatalogo(String frase, Pageable pageable) {

        if(frase != null) {
            return catalogoRepositoryInt.mostrarCatalogo(frase, pageable);
        }else{
            return catalogoRepositoryInt.findAll(pageable);
        }
    }

    @Override
    public List<Catalogo> mostrarCatalogo() {
        return null;
    }

    @Override
    public String buscar(String dato,String buscar) {
        return catalogoRespositoryInterface.buscar(dato, buscar);
    }

    public String save(Catalogo catalogo){
         catalogoRepositoryInt.save(catalogo);
         return "0";
    }

    public String delete(Catalogo id){
        catalogoRepositoryInt.delete(id);
        return "true";
    }
    @Override
    public int searchLike(String buscar){
        return catalogoRespositoryInterface.searchLike(buscar);
    }

    public Boolean validarCodigo(String codigo) {
        int tamañoCodigo = codigo.length();
        if (tamañoCodigo == 1) {
            return true;
        } else if (tamañoCodigo == 2) {
            String verificarCodigo = codigo.substring(0,1);
            int size = catalogoRespositoryInterface.searchLike(verificarCodigo);
            if (size >= 1) {
                return true;
            } else {
                return false;
            }

        } else if((tamañoCodigo%2) == 1){
            String verificarCodigo = codigo.substring(0,tamañoCodigo - 3);
            int size = catalogoRespositoryInterface.searchLike(verificarCodigo);
            if (size >= 1) {
                return true;
            } else {
                return false;
            }
        }else{
            String verificarCodigo = codigo.substring(0,tamañoCodigo - 2);
            int size = catalogoRespositoryInterface.searchLike(verificarCodigo);
            if (size >= 1) {
                return true;
            } else {
                return false;
            }
        }
    }

}

