package com.SistemaContable.model.JavierAyala;

import com.SistemaContable.model.DiegoMejia.RegistroPartida;
import lombok.Data;
import org.springframework.lang.NonNullFields;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.text.DecimalFormat;

@Entity
@Table(name="RegistrosEstadosResultado")
@Data
public class RegistrosEstadosResultado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "total_cuentas")
    @Null
    private float total_cuentas;

    @Column(name = "total_cuentas_tercer")
    @Null
    private float total_cuentas_tercer;

    @Column(name = "total_cuentas_segundo")

    private float total_cuentas_segundo;

    @ManyToOne
    @JoinColumn(name = "catalogo_id")
    private Catalogo catalogo;
    @ManyToOne
    @JoinColumn(name = "id_cierre")
    private CicloContable cicloContable;


}
