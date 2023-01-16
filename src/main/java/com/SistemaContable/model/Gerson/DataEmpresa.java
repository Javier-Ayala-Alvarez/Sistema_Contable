package com.SistemaContable.model.Gerson;

import com.SistemaContable.model.JavierAyala.Empresa;
import javax.persistence.*;

@Entity
@Table(name = "dataEmpresa")
public class DataEmpresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombreEmpresa;

    @Column(nullable = false)
    private String direccionEmpresa;

    @Column(nullable = false)
    private Integer telefonoEmpresa;

    @Column(nullable = false)
    private String emailEmpresa;

    public DataEmpresa() {
    }

    public DataEmpresa(Long id, String nombreEmpresa, String direccionEmpresa, Integer telefonoEmpresa, String emailEmpresa) {
        this.id = id;
        this.nombreEmpresa = nombreEmpresa;
        this.direccionEmpresa = direccionEmpresa;
        this.telefonoEmpresa = telefonoEmpresa;
        this.emailEmpresa = emailEmpresa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getDireccionEmpresa() {
        return direccionEmpresa;
    }

    public void setDireccionEmpresa(String direccionEmpresa) {
        this.direccionEmpresa = direccionEmpresa;
    }

    public Integer getTelefonoEmpresa() {
        return telefonoEmpresa;
    }

    public void setTelefonoEmpresa(Integer telefonoEmpresa) {
        this.telefonoEmpresa = telefonoEmpresa;
    }

    public String getEmailEmpresa() {
        return emailEmpresa;
    }

    public void setEmailEmpresa(String emailEmpresa) {
        this.emailEmpresa = emailEmpresa;
    }
}
