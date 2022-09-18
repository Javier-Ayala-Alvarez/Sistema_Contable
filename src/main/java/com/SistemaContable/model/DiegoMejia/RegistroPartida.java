package com.SistemaContable.model.DiegoMejia;

import com.SistemaContable.model.JavierAyala.Catalogo;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="registro_partida")
public class RegistroPartida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal debe;

    @Column(nullable = false)
    private BigDecimal haber;

    @ManyToOne()
    @JoinColumn(name = "partida_id")
    private Partida partida ;

    @ManyToOne()
    @JoinColumn(name = "catalogo_id")
    private Catalogo catalogo;

    public RegistroPartida() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }
}
