package com.SistemaContable.servicio.JavierAyala.ServicesImplements;

import com.SistemaContable.Repository.JavierAyala.Interfaces.EstadoResultadoInt;
import com.SistemaContable.model.JavierAyala.Catalogo;
import com.SistemaContable.model.JavierAyala.RegistrosEstadosResultado;
import com.SistemaContable.servicio.JavierAyala.Interfaces.EstadoResultadoInter;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

@Service
public class EstadoResultadoImpl implements EstadoResultadoInter {
    @Autowired
    EstadoResultadoInt estadoResultadoInt;

    @Override
    public List<RegistrosEstadosResultado> mostrar(Integer dato) {

            return estadoResultadoInt.mostrar(dato);


    }



    public RegistrosEstadosResultado mostrarId(String id) {

            return estadoResultadoInt.mostrarId(id);


    }

}
