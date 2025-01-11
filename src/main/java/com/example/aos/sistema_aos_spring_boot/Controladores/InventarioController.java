package com.example.aos.sistema_aos_spring_boot.Controladores;


import com.example.aos.sistema_aos_spring_boot.Modelo.Inventario;
import com.example.aos.sistema_aos_spring_boot.Servicios.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventario")
@CrossOrigin("*")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;


    @PostMapping("/")
    public ResponseEntity<Inventario> guardarInventario(@RequestBody Inventario inventario){
        Inventario inventarioGuardado = inventarioService.agregarInventario(inventario);
        return ResponseEntity.ok(inventarioGuardado);
    }


    @GetMapping("/{inventarioId}")
    public Inventario listarInventarioPorId(@PathVariable("inventarioId") Long inventarioId){
        return inventarioService.obtenerInventario(inventarioId);
    }


    @GetMapping("/page/{page}")
    public ResponseEntity<Page<Inventario>> listarInventarioPage(@PathVariable("page") int page) {

        if (page < 0) {
            return ResponseEntity.badRequest().build();
        }

        try {
            Sort sort = Sort.by(Sort.Order.desc("inventarioId"));
            PageRequest pageRequest = PageRequest.of(page, 8, sort);
            Page<Inventario> inventariosPage = inventarioService.findAll(pageRequest);

            if (inventariosPage.isEmpty() && page > 0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return ResponseEntity.ok(inventariosPage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/")
    public ResponseEntity<?> listarInventarios(){
        return ResponseEntity.ok(inventarioService.obtenerInventarios());
    }


    @PutMapping("/")
    public Inventario actualizarInventario(@RequestBody Inventario inventario){
        return inventarioService.actualizarInventario(inventario);
    }


    @DeleteMapping("/{inventarioId}")
    public void eliminarInventario(@PathVariable("inventarioId") Long inventarioId){
        inventarioService.eliminarInventario(inventarioId);
    }
}
