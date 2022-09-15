package com.SistemaContable.Repository.JavierAyala.RepositoryImplement;

import com.SistemaContable.Repository.JavierAyala.Interfaces.CatalogoRepositoryInterface;
import com.SistemaContable.model.JavierAyala.Catalogo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CatalogoRespository  implements CatalogoRepositoryInterface {
    @PersistenceContext
    EntityManager entityManager;

    public List<Catalogo> mostrarCatalogo() {
        String query = "FROM Catalogo ORDER BY codigo ASC";
        List<Catalogo> resultado = entityManager.createQuery(query).getResultList();
        return resultado;
    }

    public List<Catalogo> buscar(String buscar) {
        String query = "FROM Catalogo where codigo = '" + buscar + "' or nombre = '" + buscar + "' or saldoCuenta = '" + buscar + "' ORDER BY codigo ASC";
        List<Catalogo> resultado = entityManager.createQuery(query).getResultList();
        return resultado;
    }

    public String buscar(String valor, String buscar) {
        if (buscar != "") {
            String query = "FROM Catalogo where " + valor + " = '" + buscar + "'";
            List<Catalogo> resultado = entityManager.createQuery(query).getResultList();
            if (resultado.size() == 0) {
                return "true";
            } else {
                return "false";
            }

        } else {
            return "false";
        }


    }

    public int searchLike(String buscar) {
        int resultadofinal = 0;
        if (buscar != "") {
            //
            String query = " select c FROM Catalogo as c WHERE c.codigo LIKE '" + buscar + "%'";
            List<Catalogo> resultado = entityManager.createQuery(query).getResultList();
            resultadofinal = resultado.size();
        }
        return resultadofinal;
    }


}
