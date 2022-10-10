package com.SistemaContable.model.JavierAyala;

import com.SistemaContable.model.DiegoMejia.RegistroPartida;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "catalogo")
@Data//agrega los get y set
public class Catalogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "codigo", length = 8)//validando, nombre del campo, tamaño de 8 y debe ser unico
    private String codigo;


    @Column(name = "nombre")//validando, nombre del campo, tamaño de 8 y debe ser unico
    //@Min(3)//minimo 5 carácteres
    private String nombre;
    @Column(name = "saldoCuenta")
    private String saldoCuenta;

    @Column(name = "tipoCuenta")
    private String tipoCuenta;

    @OneToMany(mappedBy = "catalogo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RegistroPartida> registroPartidas;


    @Column(name = "descripcion")
    private String descripcion;




}
