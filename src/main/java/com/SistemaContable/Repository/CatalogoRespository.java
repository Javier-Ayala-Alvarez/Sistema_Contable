package com.SistemaContable.Repository;

import com.SistemaContable.model.Catalogo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.List;

@Repository
@Transactional
public class CatalogoRespository {
    @PersistenceContext
    EntityManager entityManager;

    public List<Catalogo> mostrarCatalogo() {
        String query = "FROM Catalogo ORDER BY codigo ASC";
        List<Catalogo> resultado = entityManager.createQuery(query).getResultList();
        return resultado;
    }
    public List<Catalogo> buscar(String buscar) {
        String query = "FROM Catalogo where codigo = "+buscar+" or nombre = "+buscar+" or saldo_cuenta = "+buscar+" ORDER BY codigo ASC;";
        List<Catalogo> resultado = entityManager.createQuery(query).getResultList();
        return resultado;
    }
}
