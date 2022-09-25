package com.SistemaContable.model.JavierAyala;

import com.SistemaContable.model.DiegoMejia.Partida;
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

    @Column(name = "fecha_fin")
    private Date fecha_fin;

    @Column(name = "total")
    private double total;


}
