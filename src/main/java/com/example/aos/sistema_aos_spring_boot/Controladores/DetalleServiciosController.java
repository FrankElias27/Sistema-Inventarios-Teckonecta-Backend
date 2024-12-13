package com.example.aos.sistema_aos_spring_boot.Controladores;


import com.example.aos.sistema_aos_spring_boot.Modelo.*;
import com.example.aos.sistema_aos_spring_boot.Servicios.DetalleServiciosService;
import com.example.aos.sistema_aos_spring_boot.Servicios.DetalleVentaService;
import com.example.aos.sistema_aos_spring_boot.Servicios.ServiciosService;
import com.example.aos.sistema_aos_spring_boot.Servicios.VentasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/detalleservicios")
@CrossOrigin("*")
public class DetalleServiciosController {

    @Autowired
    private DetalleServiciosService detalleServiciosService;

    @Autowired
    private ServiciosService serviciosService;

    @PostMapping("/")
    public ResponseEntity<DetalleServicios> guardarDetalleServicios(@RequestBody DetalleServicios detalleServicios){
        return ResponseEntity.ok(detalleServiciosService.agregarDetalleServicios(detalleServicios));
    }

    @PutMapping("/")
    public ResponseEntity<DetalleServicios> actualizarDetalleServicios(@RequestBody DetalleServicios detalleServicios){
        return ResponseEntity.ok(detalleServiciosService.actualizarDetalleServicios(detalleServicios));
    }

    @GetMapping("/")
    public ResponseEntity<?> listarDetalleServicios(){
        return ResponseEntity.ok(detalleServiciosService.obtenerDetalleServicios());
    }

    @GetMapping("/{detalleServiciosId}")
    public DetalleServicios listarDetalleServicios(@PathVariable("detalleServiciosId") Long detalleServiciosId){
        return detalleServiciosService.obtenerDetalleServicio(detalleServiciosId);
    }

    @DeleteMapping("/{detalleServicioIdId}")
    public void eliminarDetalleServicioId(@PathVariable("detalleServicioIdId") Long detalleServicioId){
        detalleServiciosService.eliminarDetalleServicios(detalleServicioId);
    }


    @GetMapping("/cotizacion/todos/{cotizacionId}")
    public ResponseEntity<?> listarDetalleServiciosDelCotizacionComoAdministrador(@PathVariable("cotizacionId") Long cotizacionId){
        Cotizacion cotizacion = new Cotizacion();
        cotizacion.setCotizacionId(cotizacionId);
        Set<DetalleServicios> detalleServicios = detalleServiciosService.obtenerDetalleServiciosDelCotizacion(cotizacion);
        return ResponseEntity.ok(detalleServicios);
    }
}
