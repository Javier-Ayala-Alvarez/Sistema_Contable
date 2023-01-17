package com.SistemaContable.model.JavierAyala;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CicloContable")
@Data
public class CicloContable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fechaInicio")
    private Date fechaInicio;

    @Column(name = "fecha_fin")
    private Date fecha_fin;

    @Column(name = "total")
    private Double total;

    @Column(name = "estado")
    private Boolean estado;


}
