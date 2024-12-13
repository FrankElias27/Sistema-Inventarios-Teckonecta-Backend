package com.example.aos.sistema_aos_spring_boot.Servicios;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import com.example.aos.sistema_aos_spring_boot.Modelo.ReporteStock;

import net.sf.jasperreports.engine.JRException;

public interface ReporteStockService {
    /**
     * @author <a href="mailto:4softwaredevelopers@gmail.com">Jordy Rodríguezr</a>
     * @date 24 sep. 2021
     * @description
     * @HU_CU_REQ
     * @param params
     * @return
     */
    ReporteStock obtenerReporteStock(Map<String, Object> params) throws JRException, IOException, SQLException;
    ReporteStock obtenerReporteStockTotal(Map<String, Object> params) throws JRException, IOException, SQLException;
    ReporteStock obtenerReporteStockNormal(Map<String, Object> params) throws JRException, IOException, SQLException;
    ReporteStock obtenerCotizacionSencilla(Map<String, Object> params) throws JRException, IOException, SQLException;
    ReporteStock obtenerCotizacionDetallada(Map<String, Object> params) throws JRException, IOException, SQLException;
}
