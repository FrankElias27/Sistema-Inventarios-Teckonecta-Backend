package com.example.aos.sistema_aos_spring_boot.Controladores;

import com.example.aos.sistema_aos_spring_boot.Modelo.Cotizacion;
import com.example.aos.sistema_aos_spring_boot.Modelo.DetalleCotizacion;
import com.example.aos.sistema_aos_spring_boot.Modelo.DetalleVenta;
import com.example.aos.sistema_aos_spring_boot.Modelo.Ventas;
import com.example.aos.sistema_aos_spring_boot.Servicios.CotizacionService;
import com.example.aos.sistema_aos_spring_boot.Servicios.DetalleCotizacionService;
import com.example.aos.sistema_aos_spring_boot.Servicios.DetalleVentaService;
import com.example.aos.sistema_aos_spring_boot.Servicios.VentasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/detallecotizacion")
@CrossOrigin("*")
public class DetalleCotizacionController {

    @Autowired
    private DetalleCotizacionService detalleCotizacionService;

    @Autowired
    private CotizacionService cotizacionService;

    @PostMapping("/")
    public ResponseEntity<DetalleCotizacion> guardarDetalleCotizacion(@RequestBody DetalleCotizacion detalleCotizacion){
        return ResponseEntity.ok(detalleCotizacionService.agregarDetalleCotizacion(detalleCotizacion));
    }

    @PutMapping("/")
    public ResponseEntity<DetalleCotizacion> actualizarDetalleCotizacion(@RequestBody DetalleCotizacion detalleCotizacion){
        return ResponseEntity.ok(detalleCotizacionService.actualizarDetalleCotizacion(detalleCotizacion));
    }

    @GetMapping("/")
    public ResponseEntity<?> listarDetalleCotizacion(){
        return ResponseEntity.ok(detalleCotizacionService.obtenerDetalleCotizaciones());
    }

    @GetMapping("/{detalleCotizacionId}")
    public DetalleCotizacion listarDetalleCotizacion(@PathVariable("detalleCotizacionId") Long detalleCotizacionId){
        return detalleCotizacionService.obtenerDetalleCotizacion(detalleCotizacionId);
    }

    @DeleteMapping("/{detalleCotizacionIdId}")
    public void eliminarDetalleCotizacionId(@PathVariable("detalleCotizacionIdId") Long detalleCotizacionId){
        detalleCotizacionService.eliminarDetalleCotizacion(detalleCotizacionId);
    }

    @GetMapping("/cotizacion/{cotizacionId}")
    public ResponseEntity<?> listarDetalleCotizacionDelCotizacion(@PathVariable("cotizacionId") Long cotizacionId){
        Cotizacion cotizacion = cotizacionService.obtenerCotizacion(cotizacionId);
        Set<DetalleCotizacion> detalleCotizacions = cotizacion.getDetalleCotizacion();

        List cotizacions = new ArrayList(detalleCotizacions);

        if(cotizacions.size() > Integer.parseInt(cotizacion.getNumTotaldeDetalle())){
            cotizacions = cotizacions.subList(0,Integer.parseInt(cotizacion.getNumTotaldeDetalle() + 1));
        }

        Collections.shuffle(cotizacions);
        return ResponseEntity.ok(cotizacions);
    }

    @GetMapping("/cotizacion/todos/{cotizacionId}")
    public ResponseEntity<?> listarDetalleCotizacionDelCotizacionComoAdministrador(@PathVariable("cotizacionId") Long cotizacionId){
        Cotizacion cotizacion = new Cotizacion();
        cotizacion.setCotizacionId(cotizacionId);
        Set<DetalleCotizacion> detalleCotizacions = detalleCotizacionService.obtenerDetalleCotizacionDelCotizacion(cotizacion);
        return ResponseEntity.ok(detalleCotizacions);
    }

}
