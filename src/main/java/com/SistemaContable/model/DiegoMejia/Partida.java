package com.SistemaContable.model.DiegoMejia;

import com.SistemaContable.model.JavierAyala.CicloContable;
import com.SistemaContable.model.JavierAyala.Empresa;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="partida")

public class Partida {
    @Id
    private Long id;

    private Date fecha;

    @Column(length = 255)
    private String descripcion;

    @Column()
    private boolean activo;
    @ManyToOne
    @JoinColumn(name = "id_Empresa")
    private Empresa empresa;
    @ManyToOne
    @JoinColumn(name = "id_cierre")
    private CicloContable cicloContable;
    @OneToMany(mappedBy = "partida", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RegistroPartida> registroPartidas;

    public Partida() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<RegistroPartida> getRegistroPartidas() {
        return registroPartidas;
    }

    public void setRegistroPartidas(List<RegistroPartida> registroPartidas) {
        this.registroPartidas = registroPartidas;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
