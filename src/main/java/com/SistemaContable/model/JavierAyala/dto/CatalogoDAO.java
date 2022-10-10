package com.SistemaContable.model.JavierAyala.dto;

import com.SistemaContable.model.DiegoMejia.RegistroPartida;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;

public class CatalogoDAO {
    private Integer id;
    private String codigo;
    private String nombre;
    private String saldoCuenta;
    private String tipoCuenta;
    private String descripcion;
}
