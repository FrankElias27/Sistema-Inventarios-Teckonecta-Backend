package com.example.aos.sistema_aos_spring_boot.Controladores;

import com.example.aos.sistema_aos_spring_boot.Exceptions.AsesorVentaExistenteException;
import com.example.aos.sistema_aos_spring_boot.Modelo.AsesorVenta;
import com.example.aos.sistema_aos_spring_boot.Modelo.Compras;
import com.example.aos.sistema_aos_spring_boot.Modelo.DetalleCompra;
import com.example.aos.sistema_aos_spring_boot.Modelo.Proveedor;
import com.example.aos.sistema_aos_spring_boot.Servicios.AsesorVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityNotFoundException;
import java.util.Set;

@RestController
@RequestMapping("/api/asesorventa")
@CrossOrigin("*")
public class AsesorVentaController {

    @Autowired
    private AsesorVentaService asesorVentaService;

    @PostMapping("/")
    public ResponseEntity<?> guardarAsesorVenta(@RequestBody AsesorVenta asesorVenta) {
        try {
            AsesorVenta asesorVentaGuardado = asesorVentaService.agregarAsesorVenta(asesorVenta);
            return ResponseEntity.ok(asesorVentaGuardado);
        } catch (AsesorVentaExistenteException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado al guardar el asesor de ventas.");
        }
    }

    @GetMapping("/{asesorVentaId}")
    public AsesorVenta listarAsesorVentaPorId(@PathVariable("asesorVentaId") Long asesorVentaId){
        return asesorVentaService.obtenerAsesorVenta(asesorVentaId);
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<Page<AsesorVenta>> listarAsesorVentaPage(@PathVariable("page") int page) {

        if (page < 0) {
            return ResponseEntity.badRequest().build();
        }

        try {
            Sort sort = Sort.by(Sort.Order.desc("asesorVentaId"));
            PageRequest pageRequest = PageRequest.of(page, 8, sort);
            Page<AsesorVenta> asesorVentaPage = asesorVentaService.findAll(pageRequest);

            if (asesorVentaPage.isEmpty() && page > 0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return ResponseEntity.ok(asesorVentaPage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> listarAsesoresVenta(){
        return ResponseEntity.ok(asesorVentaService.obtenerAsesoresVenta());
    }

    @PutMapping("/")
    public ResponseEntity<?> actualizarAsesorVenta(@RequestBody AsesorVenta asesorVenta) {
        try {
            AsesorVenta asesorVentaActualizado = asesorVentaService.actualizarAsesorVenta(asesorVenta);
            return ResponseEntity.ok(asesorVentaActualizado);
        } catch (AsesorVentaExistenteException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado al actualizar el asesor de ventas.");
        }
    }

    @DeleteMapping("/{asesorVentaId}")
    public void eliminarAsesorVenta(@PathVariable("asesorVentaId") Long asesorVentaId){
        asesorVentaService.eliminarAsesorVenta(asesorVentaId);
    }

    @GetMapping("/proveedor/todos/{proveedorId}")
    public ResponseEntity<?> listarAsesoresDelProveedorComoAdministrador(@PathVariable("proveedorId") Long proveedorId){
        Proveedor proveedor = new Proveedor();
        proveedor.setProveedorId(proveedorId);
        Set<AsesorVenta> asesores = asesorVentaService.obtenerAsesorVentaDelProveedor(proveedor);
        return ResponseEntity.ok(asesores);
    }
}
