package com.SistemaContable.servicio.JavierAyala.ServicesImplements;

import com.SistemaContable.Repository.JavierAyala.Interfaces.CatalogoRepositoryInt;
import com.SistemaContable.Repository.JavierAyala.Interfaces.CatalogoRepositoryInterface;
import com.SistemaContable.model.JavierAyala.Catalogo;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.List;

@Service
public class CatalogoServices implements CatalogoRepositoryInterface {

    @Autowired

    CatalogoRepositoryInterface catalogoRespositoryInterface;
    @Autowired
    CatalogoRepositoryInt catalogoRepositoryInt;


    public Page<Catalogo> mostrarCatalogo(String frase, Pageable pageable) {

        if (frase != null) {
            return catalogoRepositoryInt.mostrarCatalogo(frase, pageable);
        } else {
            return catalogoRepositoryInt.findAll(pageable);
        }
    }

    @Override
    public List<Catalogo> mostrarCatalogo() {
        return null;
    }

    @Override
    public String buscar(String dato, String buscar) {
        return catalogoRespositoryInterface.buscar(dato, buscar);
    }

    public String save(Catalogo catalogo) {
        catalogoRepositoryInt.save(catalogo);
        return "0";
    }

    public String delete(Catalogo id) {
        catalogoRepositoryInt.delete(id);
        return "true";
    }

    @Override
    public int searchLike(String buscar) {
        return catalogoRespositoryInterface.searchLike(buscar);
    }

    public Boolean validarCodigo(String codigo) {
        int tamañoCodigo = codigo.length();
        if (tamañoCodigo == 1) {
            return true;
        } else if (tamañoCodigo == 2) {
            String verificarCodigo = codigo.substring(0, 1);
            int size = catalogoRespositoryInterface.searchLike(verificarCodigo);
            if (size >= 1) {
                return true;
            } else {
                return false;
            }

        } else if ((tamañoCodigo % 2) == 1) {
            String verificarCodigo = codigo.substring(0, tamañoCodigo - 3);
            int size = catalogoRespositoryInterface.searchLike(verificarCodigo);
            if (size >= 1) {
                return true;
            } else {
                return false;
            }
        } else {
            String verificarCodigo = codigo.substring(0, tamañoCodigo - 2);
            int size = catalogoRespositoryInterface.searchLike(verificarCodigo);
            if (size >= 1) {
                return true;
            } else {
                return false;
            }
        }
    }

    public ResponseEntity<byte[]> exportReport() throws FileNotFoundException, JRException {
        try {
            List<Catalogo> catalogo = catalogoRepositoryInt.findAll(Sort.by("codigo").ascending());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(catalogo);

            JasperPrint empReport = JasperFillManager.fillReport(JasperCompileManager.compileReport(ResourceUtils.getFile("classpath:ReporteCatalogo.jrxml").getAbsolutePath()) // path of the jasper report
                    , null //empParams dynamic parameters
                    ,dataSource);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);


                headers.setContentDispositionFormData("filename", "Catalogo.pdf");


            return new ResponseEntity<byte[]>(JasperExportManager.exportReportToPdf(empReport), headers, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}


