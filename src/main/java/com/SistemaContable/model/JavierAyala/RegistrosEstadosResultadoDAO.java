package com.SistemaContable.model.JavierAyala;

import lombok.Data;

import javax.persistence.*;
import java.nio.FloatBuffer;
import java.text.DecimalFormat;
@Data
public class RegistrosEstadosResultadoDAO {

    private Integer id;
    private Float total_cuentas;
    private Float total_cuentas_tercer;
    private Float total_cuentas_segundo;
    private Catalogo catalogo;
    private CicloContable cicloContable;


}
