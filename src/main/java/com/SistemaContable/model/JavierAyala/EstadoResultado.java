package com.SistemaContable.model.JavierAyala;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "EstadoResultado")

@Data
public class EstadoResultado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "dato1")
    private String dato1;
    @Column(name = "dato2")
    private String dato2;
    @Column(name = "dato3")
    private String dato3;

    @Column(name = "fecha")
    private String fecha;

    public EstadoResultado(String nombre, String dato1, String dato2, String dato3, String fecha) {
        this.nombre = nombre;
        this.dato1 = dato1;
        this.dato2 = dato2;
        this.dato3 = dato3;
        this.fecha = fecha;
    }

    public EstadoResultado() {

    }
}
