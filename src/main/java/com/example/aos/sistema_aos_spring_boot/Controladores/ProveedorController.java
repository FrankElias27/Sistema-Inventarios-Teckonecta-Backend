package com.example.aos.sistema_aos_spring_boot.Controladores;

import com.example.aos.sistema_aos_spring_boot.Exceptions.ProveedorExistenteException;
import com.example.aos.sistema_aos_spring_boot.Modelo.Categoria;
import com.example.aos.sistema_aos_spring_boot.Modelo.Cliente;
import com.example.aos.sistema_aos_spring_boot.Modelo.Proveedor;
import com.example.aos.sistema_aos_spring_boot.Servicios.ClienteService;
import com.example.aos.sistema_aos_spring_boot.Servicios.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/proveedor")
@CrossOrigin("*")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @PostMapping("/")
    public ResponseEntity<?> guardarProveedor(@RequestBody Proveedor proveedor) {
        try {
            Proveedor proveedorGuardado = proveedorService.agregarProveedor(proveedor);
            return ResponseEntity.ok(proveedorGuardado);
        } catch (ProveedorExistenteException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado al guardar el proveedor.");
        }
    }

    @GetMapping("/{proveedorId}")
    public Proveedor listarProveedorPorId(@PathVariable("proveedorId") Long proveedorId){
        return proveedorService.obtenerProveedor(proveedorId);
    }


    @GetMapping("/page/{page}")
    public ResponseEntity<Page<Proveedor>> listarProveedoresPage(@PathVariable("page") int page) {

        if (page < 0) {
            return ResponseEntity.badRequest().build();
        }

        try {
            Sort sort = Sort.by(Sort.Order.desc("proveedorId"));
            PageRequest pageRequest = PageRequest.of(page, 8, sort);
            Page<Proveedor> proveedorPage = proveedorService.findAll(pageRequest);

            if (proveedorPage.isEmpty() && page > 0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return ResponseEntity.ok(proveedorPage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> listarProveedores(){
        return ResponseEntity.ok(proveedorService.obtenerProveedores());
    }

    @PutMapping("/")
    public ResponseEntity<?> actualizarProveedor(@RequestBody Proveedor proveedor) {
        try {
            Proveedor proveedorActualizado = proveedorService.actualizarProveedor(proveedor);
            return ResponseEntity.ok(proveedorActualizado);
        } catch (ProveedorExistenteException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado al actualizar el proveedor.");
        }
    }

    @DeleteMapping("/{proveedorId}")
    public void eliminarProveedor(@PathVariable("proveedorId") Long proveedorId){
        proveedorService.eliminarProveedor(proveedorId);
    }
}
