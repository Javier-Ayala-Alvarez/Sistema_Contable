package com.SistemaContable.model.JavierAyala;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Null;
import javax.validation.valueextraction.ExtractedValue;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getTotal_cuentas() {
        return total_cuentas;
    }

    public void setTotal_cuentas(float total_cuentas) {
        this.total_cuentas = total_cuentas;
    }

    public float getTotal_cuentas_tercer() {
        return total_cuentas_tercer;
    }

    public void setTotal_cuentas_tercer(float total_cuentas_tercer) {
        this.total_cuentas_tercer = total_cuentas_tercer;
    }

    public float getTotal_cuentas_segundo() {
        return total_cuentas_segundo;
    }

    public void setTotal_cuentas_segundo(float total_cuentas_segundo) {
        this.total_cuentas_segundo = total_cuentas_segundo;
    }

    public Catalogo getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(Catalogo catalogo) {
        this.catalogo = catalogo;
    }

    public CicloContable getCicloContable() {
        return cicloContable;
    }

    public void setCicloContable(CicloContable cicloContable) {
        this.cicloContable = cicloContable;
    }
}
