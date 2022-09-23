package com.SistemaContable.model.JavierAyala;

import com.SistemaContable.model.DiegoMejia.RegistroPartida;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="empresa")
@Data
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String nombre;
    @Column
    private String descripcion;


}
