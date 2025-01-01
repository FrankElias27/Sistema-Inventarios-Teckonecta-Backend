package com.example.aos.sistema_aos_spring_boot.Servicios.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aos.sistema_aos_spring_boot.Commons.JasperReportManager;
import com.example.aos.sistema_aos_spring_boot.Enums.TipoReporteEnum;
import com.example.aos.sistema_aos_spring_boot.Modelo.Reportes;
import com.example.aos.sistema_aos_spring_boot.Servicios.ReporteService;

import net.sf.jasperreports.engine.JRException;

/**
 * @author <a href="mailto:4softwaredevelopers@gmail.com">Jordy Rodríguezr</a>
 * @project demo-spring-boot-jasper
 * @class ReporteStockImpl
 * @description
 * @HU_CU_REQ
 * @date 24 sep. 2021
 */
@Service
public class ReporteServiceImpl implements ReporteService {

    @Autowired
    private JasperReportManager reportManager;

    @Autowired
    private DataSource dataSource;

    private Reportes generateReport(String fileName, Map<String, Object> params) throws JRException, IOException, SQLException {
        Reportes dto = new Reportes();
        String extension = params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name()) ? ".xlsx" : ".pdf";
        dto.setFileName(fileName + extension);

        try (Connection connection = dataSource.getConnection();
             ByteArrayOutputStream stream = reportManager.export(fileName, params.get("tipo").toString(), params, connection)) {

            byte[] bs = stream.toByteArray();
            dto.setStream(new ByteArrayInputStream(bs));
            dto.setLength(bs.length);
        }

        return dto;
    }

    @Override
    public Reportes obtenerReporteStock(Map<String, Object> params) throws JRException, IOException, SQLException {
        return generateReport("ReporteRojasCategoria", params);
    }

    @Override
    public Reportes obtenerReporteStockTotal(Map<String, Object> params) throws JRException, IOException, SQLException {
        return generateReport("ReporteRojasTotal", params);
    }

    @Override
    public Reportes obtenerReporteStockNormal(Map<String, Object> params) throws JRException, IOException, SQLException {
        return generateReport("ReporteRojasNormal", params);
    }

    @Override
    public Reportes obtenerCotizacionSencilla(Map<String, Object> params) throws JRException, IOException, SQLException {
        return generateReport("CotizacionTeccuida", params);
    }

    @Override
    public Reportes obtenerCotizacionDetallada(Map<String, Object> params) throws JRException, IOException, SQLException {
        return generateReport("CotizacionTeccuida2", params);
    }
}