package com.SistemaContable.model.JavierAyala;

import lombok.Data;

@Data
public class RegistrosEstadosResultadoDAO {

    private Integer id;
    private Float total_cuentas;
    private Float total_cuentas_tercer;
    private Float total_cuentas_segundo;
    private Catalogo catalogo;
    private CicloContable cicloContable;


    public RegistrosEstadosResultadoDAO(Integer id, Float total_cuentas, Float total_cuentas_tercer, Float total_cuentas_segundo, Catalogo catalogo, CicloContable cicloContable) {
        this.id = id;
        this.total_cuentas = total_cuentas;
        this.total_cuentas_tercer = total_cuentas_tercer;
        this.total_cuentas_segundo = total_cuentas_segundo;
        this.catalogo = catalogo;
        this.cicloContable = cicloContable;
    }
}
