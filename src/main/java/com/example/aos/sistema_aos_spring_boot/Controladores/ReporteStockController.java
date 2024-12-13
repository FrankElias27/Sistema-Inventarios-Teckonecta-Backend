package com.example.aos.sistema_aos_spring_boot.Controladores;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import com.example.aos.sistema_aos_spring_boot.Servicios.ReporteStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.aos.sistema_aos_spring_boot.Enums.TipoReporteEnum;
import com.example.aos.sistema_aos_spring_boot.Modelo.ReporteStock;
import com.example.aos.sistema_aos_spring_boot.Servicios.ReporteStockService;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/api/report")
@CrossOrigin("*")
public class ReporteStockController {
    @Autowired
    private ReporteStockService reporteStockService;

    @GetMapping(path = "/stock/download")
    public ResponseEntity<Resource> download(@RequestParam Map<String, Object> params)
            throws JRException, IOException, SQLException {
        ReporteStock dto = reporteStockService.obtenerReporteStock(params);

        InputStreamResource streamResource = new InputStreamResource(dto.getStream());
        MediaType mediaType = null;
        if (params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name())) {
            mediaType = MediaType.APPLICATION_OCTET_STREAM;
        } else {
            mediaType = MediaType.APPLICATION_PDF;
        }

        return ResponseEntity.ok().header("Content-Disposition", "inline; filename=\"" + dto.getFileName() + "\"")
                .contentLength(dto.getLength()).contentType(mediaType).body(streamResource);
    }
    @GetMapping(path = "/stock/downloads")
    public ResponseEntity<Resource> downloads(@RequestParam Map<String, Object> params)
            throws JRException, IOException, SQLException {
        ReporteStock dto = reporteStockService.obtenerReporteStockTotal(params);

        InputStreamResource streamResource = new InputStreamResource(dto.getStream());
        MediaType mediaType = null;
        if (params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name())) {
            mediaType = MediaType.APPLICATION_OCTET_STREAM;
        } else {
            mediaType = MediaType.APPLICATION_PDF;
        }

        return ResponseEntity.ok().header("Content-Disposition", "inline; filename=\"" + dto.getFileName() + "\"")
                .contentLength(dto.getLength()).contentType(mediaType).body(streamResource);
    }
    @GetMapping(path = "/stock/downloadss")
    public ResponseEntity<Resource> downloadss(@RequestParam Map<String, Object> params)
            throws JRException, IOException, SQLException {
        ReporteStock dto = reporteStockService.obtenerReporteStockNormal(params);

        InputStreamResource streamResource = new InputStreamResource(dto.getStream());
        MediaType mediaType = null;
        if (params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name())) {
            mediaType = MediaType.APPLICATION_OCTET_STREAM;
        } else {
            mediaType = MediaType.APPLICATION_PDF;
        }

        return ResponseEntity.ok().header("Content-Disposition", "inline; filename=\"" + dto.getFileName() + "\"")
                .contentLength(dto.getLength()).contentType(mediaType).body(streamResource);
    }
    @GetMapping(path = "/cotizacion/download")
    public ResponseEntity<Resource> cotizacion(@RequestParam Map<String, Object> params)
            throws JRException, IOException, SQLException {
        ReporteStock dto = reporteStockService.obtenerCotizacionSencilla(params);

        InputStreamResource streamResource = new InputStreamResource(dto.getStream());
        MediaType mediaType = null;
        if (params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name())) {
            mediaType = MediaType.APPLICATION_OCTET_STREAM;
        } else {
            mediaType = MediaType.APPLICATION_PDF;
        }

        return ResponseEntity.ok().header("Content-Disposition", "inline; filename=\"" + dto.getFileName() + "\"")
                .contentLength(dto.getLength()).contentType(mediaType).body(streamResource);
    }

    @GetMapping(path = "/cotizacion2/download")
    public ResponseEntity<Resource> cotizacions(@RequestParam Map<String, Object> params)
            throws JRException, IOException, SQLException {
        ReporteStock dto = reporteStockService.obtenerCotizacionDetallada(params);

        InputStreamResource streamResource = new InputStreamResource(dto.getStream());
        MediaType mediaType = null;
        if (params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name())) {
            mediaType = MediaType.APPLICATION_OCTET_STREAM;
        } else {
            mediaType = MediaType.APPLICATION_PDF;
        }

        return ResponseEntity.ok().header("Content-Disposition", "inline; filename=\"" + dto.getFileName() + "\"")
                .contentLength(dto.getLength()).contentType(mediaType).body(streamResource);
    }
}
