package com.SistemaContable.Repository.AlejandroDeras;

import com.SistemaContable.model.DiegoMejia.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MayorRepositorio extends JpaRepository<Partida, Long> {

    @Query(value = "SELECT c.codigo, c.nombre, concat(EXTRACT(DAY from p.fecha),'/',EXTRACT(MONTH from p.fecha),'/',EXTRACT(YEAR from p.fecha))AS fecha, p.descripcion, rp.debe, rp.haber FROM Partida p INNER JOIN RegistroPartida rp ON p.id = rp.partida INNER JOIN Catalogo c ON c.id = rp.catalogo GROUP BY c.codigo, c.nombre, p.fecha, p.descripcion, rp.debe,rp.haber ORDER BY c.codigo", nativeQuery = true)
    public List<Object> libroMayor();
}
