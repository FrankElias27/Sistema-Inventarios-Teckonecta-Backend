package com.example.aos.sistema_aos_spring_boot.Controladores;


import com.example.aos.sistema_aos_spring_boot.Modelo.Categoria;
import com.example.aos.sistema_aos_spring_boot.Modelo.Compras;
import com.example.aos.sistema_aos_spring_boot.Modelo.Ventas;
import com.example.aos.sistema_aos_spring_boot.Servicios.ComprasService;
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
    public ResponseEntity<Page<Compras>> listarComprasPage(@PathVariable("page") int page) {

        if (page < 0) {
            return ResponseEntity.badRequest().build();
        }

        try {
            Sort sort = Sort.by(Sort.Order.desc("compraId"));
            PageRequest pageRequest = PageRequest.of(page, 8, sort);
            Page<Compras> eventosPage = comprasService.findAll(pageRequest);

            if (eventosPage.isEmpty() && page > 0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return ResponseEntity.ok(eventosPage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{comprasId}")
    public Compras listarCompra(@PathVariable("comprasId") Long comprasId){
        return comprasService.obtenerCompra(comprasId);
    }

    @DeleteMapping("/{comprasId}")
    public void eliminarCompras(@PathVariable("comprasId") Long comprasId){
        comprasService.eliminarCompra(comprasId);
    }


}
