package com.example.aos.sistema_aos_spring_boot.Controladores;


import com.example.aos.sistema_aos_spring_boot.Modelo.Compras;
import com.example.aos.sistema_aos_spring_boot.Modelo.Ventas;
import com.example.aos.sistema_aos_spring_boot.Servicios.ComprasService;
import com.example.aos.sistema_aos_spring_boot.Servicios.VentasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compra")
@CrossOrigin("*")
public class ComprasController {

    @Autowired
    private ComprasService comprasService;

    @PostMapping("/")
    public ResponseEntity<Compras> guardarCompra(@RequestBody Compras compras){
        return ResponseEntity.ok(comprasService.agregarCompra(compras));
    }

    @PutMapping("/")
    public ResponseEntity<Compras> actualizarCompras(@RequestBody Compras compras){
        return ResponseEntity.ok(comprasService.actualizarCompra(compras));
    }

    @GetMapping("/")
    public ResponseEntity<?> listarCompras(){
        return ResponseEntity.ok(comprasService.obtenerCompras());
    }

    @GetMapping("/page/{page}")
    public Page<Compras> listarCompra(@PathVariable("page") int page){
        return comprasService.findAll(PageRequest.of(page,10));
    }

    @GetMapping("/{comprasId}")
    public Compras listarCompra(@PathVariable("comprasId") Long comprasId){
        return comprasService.obtenerCompra(comprasId);
    }

    @DeleteMapping("/{comprasId}")
    public void eliminarCompras(@PathVariable("comprasId") Long comprasId){
        comprasService.eliminarCompra(comprasId);
    }


    @GetMapping("/activo")
    public List<Compras> listarComprasActivos(){
        return comprasService.obtenerComprasActivos();
    }
}
