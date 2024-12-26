package com.example.aos.sistema_aos_spring_boot.Controladores;

import com.example.aos.sistema_aos_spring_boot.Modelo.Compras;
import com.example.aos.sistema_aos_spring_boot.Modelo.Productos;
import com.example.aos.sistema_aos_spring_boot.Modelo.Ventas;
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
@RequestMapping("/api/venta")
@CrossOrigin("*")
public class VentasController {

    @Autowired
    private VentasService ventasService;

    @PostMapping("/")
    public ResponseEntity<Ventas> guardarVentas(@RequestBody Ventas ventas){
        return ResponseEntity.ok(ventasService.agregarVenta(ventas));
    }

    @PutMapping("/")
    public ResponseEntity<Ventas> actualizarVentas(@RequestBody Ventas ventas){
        return ResponseEntity.ok(ventasService.actualizarVenta(ventas));
    }

    @GetMapping("/")
    public ResponseEntity<?> listarVentas(){
        return ResponseEntity.ok(ventasService.obtenerVentas());
    }



    @GetMapping("/page/{page}")
    public ResponseEntity<Page<Ventas>> listarVentasPage(@PathVariable("page") int page) {

        if (page < 0) {
            return ResponseEntity.badRequest().build();
        }

        try {
            Sort sort = Sort.by(Sort.Order.desc("ventaId"));
            PageRequest pageRequest = PageRequest.of(page, 8, sort);
            Page<Ventas> eventosPage = ventasService.findAll(pageRequest);

            if (eventosPage.isEmpty() && page > 0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return ResponseEntity.ok(eventosPage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{ventasId}")
    public Ventas listarVenta(@PathVariable("ventasId") Long ventasId){
        return ventasService.obtenerVenta(ventasId);
    }

    @DeleteMapping("/{ventasId}")
    public void eliminarVentas(@PathVariable("ventasId") Long ventasId){
        ventasService.eliminarVenta(ventasId);
    }





}
