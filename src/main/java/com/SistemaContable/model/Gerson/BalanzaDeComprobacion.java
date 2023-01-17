package com.SistemaContable.model.Gerson;

import org.springframework.data.annotation.Immutable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Date;

@Entity
@Table
public class BalanzaDeComprobacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String cuenta;

    @Column
    private BigDecimal debe;

    @Column
    private BigDecimal haber;

    public BalanzaDeComprobacion() {
    }

    public BalanzaDeComprobacion(String cuenta, BigDecimal debe, BigDecimal haber) {
        this.cuenta = cuenta;
        this.debe = debe;
        this.haber = haber;
    }

    public Integer getId() {
        return id;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public BigDecimal getDebe() {
        return debe;
    }

    public void setDebe(BigDecimal debe) {
        this.debe = debe;
    }

    public BigDecimal getHaber() {
        return haber;
    }

    public void setHaber(BigDecimal haber) {
        this.haber = haber;
    }
}
