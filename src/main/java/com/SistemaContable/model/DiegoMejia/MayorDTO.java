package com.SistemaContable.model.DiegoMejia;

import java.math.BigDecimal;
import java.util.Objects;

public class MayorDTO {

    private String Codigocuenta;
    String nombreCuenta;
    BigDecimal debe;
    BigDecimal haber;

    public MayorDTO() {
    }

    public String getCodigocuenta() {
        return Codigocuenta;
    }

    public void setCodigocuenta(String codigocuenta) {
        Codigocuenta = codigocuenta;
    }

    public String getNombreCuenta() {
        return nombreCuenta;
    }

    public void setNombreCuenta(String nombreCuenta) {
        this.nombreCuenta = nombreCuenta;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MayorDTO mayorDTO = (MayorDTO) o;
        return Codigocuenta.equals(mayorDTO.Codigocuenta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Codigocuenta);
    }
}
