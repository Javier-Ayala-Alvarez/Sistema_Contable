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

    @Column(nullable = false)
    private String nombreEmpresa;

    @Column(nullable = false)
    private String direccionEmpresa;

    @Column(nullable = false)
    private Integer telefonoEmpresa;

    @Column(nullable = false)
    private String emailEmpresa;


}
