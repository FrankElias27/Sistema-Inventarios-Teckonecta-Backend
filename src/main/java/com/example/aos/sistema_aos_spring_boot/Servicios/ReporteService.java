package com.example.aos.sistema_aos_spring_boot.Servicios;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import com.example.aos.sistema_aos_spring_boot.Modelo.Reportes;

import net.sf.jasperreports.engine.JRException;

public interface ReporteService {
    /**
     * @author <a href="mailto:4softwaredevelopers@gmail.com">Jordy Rodríguezr</a>
     * @date 24 sep. 2021
     * @description
     * @HU_CU_REQ
     * @param params
     * @return
     */
    Reportes obtenerReporteStock(Map<String, Object> params) throws JRException, IOException, SQLException;
    Reportes obtenerReporteStockTotal(Map<String, Object> params) throws JRException, IOException, SQLException;
    Reportes obtenerReporteStockNormal(Map<String, Object> params) throws JRException, IOException, SQLException;
    Reportes obtenerCotizacionSencilla(Map<String, Object> params) throws JRException, IOException, SQLException;
    Reportes obtenerCotizacionDetallada(Map<String, Object> params) throws JRException, IOException, SQLException;
}
