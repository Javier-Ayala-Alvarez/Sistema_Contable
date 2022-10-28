package com.SistemaContable.servicio.JavierAyala.ServicesImplements;

import com.SistemaContable.Repository.JavierAyala.Interfaces.EstadoResultadoInt;
import com.SistemaContable.model.JavierAyala.Catalogo;
import com.SistemaContable.model.JavierAyala.RegistrosEstadosResultado;
import com.SistemaContable.servicio.JavierAyala.Interfaces.EstadoResultadoInter;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

@Service
public class EstadoResultadoImpl implements EstadoResultadoInter {
    @Autowired
    EstadoResultadoInt estadoResultadoInt;

    @Override
    public List<RegistrosEstadosResultado> mostrar(Integer dato) {
        if (dato != null) {
            return estadoResultadoInt.mostrar(dato);
        } else {
            Integer dato1 = 2021;
            return estadoResultadoInt.mostrar(dato1);
        }

    }



    public RegistrosEstadosResultado mostrarId(String id) {

            return estadoResultadoInt.mostrarId(id);


    }
    public ResponseEntity<byte[]> exportReport() throws FileNotFoundException, JRException {
        try {
            List<RegistrosEstadosResultado> registro =this.estadoResultadoInt.mostrar(2021); //= catalogoRepositoryInt.findAll(Sort.by("codigo").ascending());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(registro);
            final HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("nombreempresa1"," nombreempresa1");
            JasperPrint empReport = JasperFillManager.fillReport(JasperCompileManager.compileReport(ResourceUtils.getFile("classpath:EstadoResultado.jrxml").getAbsolutePath()) // path of the jasper report
                    , parameters //empParams dynamic parameters
                    ,dataSource);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);


            headers.setContentDispositionFormData("filename", "EstadoResultado.pdf");


            return new ResponseEntity<byte[]>(JasperExportManager.exportReportToPdf(empReport), headers, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
