package com.SistemaContable.model.JavierAyala;

import com.SistemaContable.model.DiegoMejia.RegistroPartida;
import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name="RegistrosEstadosResultado")
@Data
public class RegistrosEstadosResultado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "total")
    private Double total;

    @ManyToOne
    @JoinColumn(name = "catalogo_id")
    private Catalogo catalogo;
    @ManyToOne
    @JoinColumn(name = "id_cierre")
    private CicloContable cicloContable;


}
