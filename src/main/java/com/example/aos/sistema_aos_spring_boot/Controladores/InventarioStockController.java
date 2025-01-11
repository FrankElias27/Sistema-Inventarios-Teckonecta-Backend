package com.example.aos.sistema_aos_spring_boot.Controladores;

import com.example.aos.sistema_aos_spring_boot.Exceptions.ProductoRegistradoException;
import com.example.aos.sistema_aos_spring_boot.Modelo.Inventario;
import com.example.aos.sistema_aos_spring_boot.Modelo.InventarioStock;
import com.example.aos.sistema_aos_spring_boot.Servicios.InventarioService;
import com.example.aos.sistema_aos_spring_boot.Servicios.InventarioStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stock")
@CrossOrigin("*")
public class InventarioStockController {

    @Autowired
    private InventarioStockService inventarioStockService;

    @PostMapping("/")
    public ResponseEntity<?> guardarInventarioStock(@RequestBody InventarioStock inventarioStock){
        try {
            InventarioStock inventarioGuardado = inventarioStockService.agregarStock(inventarioStock);
            return ResponseEntity.ok(inventarioGuardado);
        } catch (ProductoRegistradoException e) {
            // Capturar la excepción personalizada y devolver el error adecuado
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{inventarioId}/page/{page}")
    public ResponseEntity<Page<InventarioStock>> listarInventarioStockPage(
            @PathVariable("inventarioId") Long inventarioId,
            @PathVariable("page") int page) {

        if (page < 0) {
            return ResponseEntity.badRequest().build();
        }

        try {
            // Configurar el ordenamiento por defecto
            Sort sort = Sort.by(Sort.Order.desc("stockId"));
            PageRequest pageRequest = PageRequest.of(page, 8, sort);

            // Obtener los datos paginados
            Page<InventarioStock> inventarioStockPage = inventarioStockService.getStocksByInventarioId(inventarioId, pageRequest);

            if (inventarioStockPage.isEmpty() && page > 0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return ResponseEntity.ok(inventarioStockPage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{stockId}")
    public InventarioStock listarInventarioStockPorId(@PathVariable("stockId") Long stockId){
        return inventarioStockService.obtenerStock(stockId);
    }

    @PutMapping("/")
    public InventarioStock actualizarInventarioStock(@RequestBody InventarioStock inventarioStock){
        return inventarioStockService.actualizarStock(inventarioStock);
    }

    @DeleteMapping("/{stockId}")
    public void eliminarStock(@PathVariable("stockId") Long stockId){
        inventarioStockService.eliminarInventarioStock(stockId);
    }
}
