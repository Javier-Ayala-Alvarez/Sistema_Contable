package com.SistemaContable.Repository.Gerson;

import com.SistemaContable.model.Gerson.BalanzaDeComprobacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanzaDeComprobacionRepository extends JpaRepository <BalanzaDeComprobacion, Long> {
}
