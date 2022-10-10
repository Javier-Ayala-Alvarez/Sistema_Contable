package com.SistemaContable.model.JavierAyala;

import lombok.Data;

@Data
public class RegistrosEstadosResultadoDAO {

    private Integer id;
    private String total_cuentas;
    private String total_cuentas_tercer;
    private String total_cuentas_segundo;
    private Catalogo catalogo;
    private CicloContable cicloContable;

    public RegistrosEstadosResultadoDAO() {
    }

    public RegistrosEstadosResultadoDAO(Integer id, String total_cuentas, String total_cuentas_tercer, String total_cuentas_segundo, Catalogo catalogo, CicloContable cicloContable) {
        this.id = id;
        this.total_cuentas = total_cuentas;
        this.total_cuentas_tercer = total_cuentas_tercer;
        this.total_cuentas_segundo = total_cuentas_segundo;
        this.catalogo = catalogo;
        this.cicloContable = cicloContable;
    }


    public RegistrosEstadosResultadoDAO(String total_cuentas_segundo, Catalogo catalogo, CicloContable cicloContable) {
        this.total_cuentas_segundo = total_cuentas_segundo;
        this.catalogo = catalogo;
        this.cicloContable = cicloContable;
    }

    public RegistrosEstadosResultadoDAO(String total_cuentas_tercer, String total_cuentas_segundo, Catalogo catalogo, CicloContable cicloContable) {
        this.total_cuentas_tercer = total_cuentas_tercer;
        this.total_cuentas_segundo = total_cuentas_segundo;
        this.catalogo = catalogo;
        this.cicloContable = cicloContable;
    }

    public RegistrosEstadosResultadoDAO(String total_cuentas_segundo, Catalogo catalogo) {
        this.total_cuentas_segundo = total_cuentas_segundo;
        this.catalogo = catalogo;
    }

    public RegistrosEstadosResultadoDAO(Catalogo catalogo, CicloContable cicloContable) {
        this.catalogo = catalogo;
        this.cicloContable = cicloContable;
    }

    public RegistrosEstadosResultadoDAO(Catalogo catalogo) {
        this.catalogo = catalogo;
    }
}
