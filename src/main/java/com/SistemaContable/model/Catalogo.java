package com.SistemaContable.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "catalogo")
@Data//agrega los get y set
public class Catalogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "codigo", length = 8, unique = true)//validando, nombre del campo, tamaño de 8 y debe ser unico
    @NotBlank(message = "Debe ingresar el codigo")//si esta vacio mensaje de validación
    private String codigo;


    @Column(name = "nombre", length = 50, unique = true)//validando, nombre del campo, tamaño de 8 y debe ser unico
    @NotBlank(message = "Debe ingresar el nombre")//si esta vacio mensaje de validación
    @Min(5)//minimo 5 carácteres
    private String nombre;

    @Column(name = "estado")
    //@Pattern(regexp = "Deudor"+"Acreedor" )//es correcto si tiene estos datos
    private boolean estado;

    @Column(name = "saldo_cuenta")
    private String saldoCuenta;

}