package com.example.aos.sistema_aos_spring_boot.Controladores;

import com.example.aos.sistema_aos_spring_boot.Modelo.Compras;
import com.example.aos.sistema_aos_spring_boot.Modelo.Cotizacion;
import com.example.aos.sistema_aos_spring_boot.Modelo.Ventas;
import com.example.aos.sistema_aos_spring_boot.Servicios.CotizacionService;
import com.example.aos.sistema_aos_spring_boot.Servicios.VentasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cotizacion")
@CrossOrigin("*")
public class CotizacionController {

    @Autowired
    private CotizacionService cotizacionService;

    @PostMapping("/")
    public ResponseEntity<Cotizacion> guardarCotizacion(@RequestBody Cotizacion cotizacion){
        return ResponseEntity.ok(cotizacionService.agregarCotizacion(cotizacion));
    }

    @PutMapping("/")
    public ResponseEntity<Cotizacion> actualizarCotizacion(@RequestBody Cotizacion cotizacion){
        return ResponseEntity.ok(cotizacionService.actualizarCotizacion(cotizacion));
    }

    @GetMapping("/")
    public ResponseEntity<?> listarContizacion(){
        return ResponseEntity.ok(cotizacionService.obtenerCotizaciones());
    }



    @GetMapping("/page/{page}")
    public ResponseEntity<Page<Cotizacion>> listarCotizacionPage(@PathVariable("page") int page) {

        if (page < 0) {
            return ResponseEntity.badRequest().build();
        }

        try {
            Sort sort = Sort.by(Sort.Order.desc("cotizacionId"));
            PageRequest pageRequest = PageRequest.of(page, 8, sort);
            Page<Cotizacion> eventosPage = cotizacionService.findAll(pageRequest);

            if (eventosPage.isEmpty() && page > 0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return ResponseEntity.ok(eventosPage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{cotizacionId}")
    public Cotizacion listarCotizacion(@PathVariable("cotizacionId") Long cotizacionId){
        return cotizacionService.obtenerCotizacion(cotizacionId);
    }

    @DeleteMapping("/{cotizacionId}")
    public void eliminarCotizacion(@PathVariable("cotizacionId") Long cotizacionId){
        cotizacionService.eliminarCotizacion(cotizacionId);
    }

}
