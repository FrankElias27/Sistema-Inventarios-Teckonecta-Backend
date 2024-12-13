package com.example.aos.sistema_aos_spring_boot.Controladores;

import com.example.aos.sistema_aos_spring_boot.Modelo.Cliente;
import com.example.aos.sistema_aos_spring_boot.Modelo.Proveedor;
import com.example.aos.sistema_aos_spring_boot.Servicios.ClienteService;
import com.example.aos.sistema_aos_spring_boot.Servicios.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/proveedor")
@CrossOrigin("*")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @PostMapping("/")
    public ResponseEntity<Proveedor> guardarProveedor(@RequestBody Proveedor proveedor){
        Proveedor proveedorGuardado = proveedorService.agregarProveedor(proveedor);
        return ResponseEntity.ok(proveedorGuardado);
    }

    @GetMapping("/{proveedorId}")
    public Proveedor listarProveedorPorId(@PathVariable("proveedorId") Long proveedorId){
        return proveedorService.obtenerProveedor(proveedorId);
    }

    @GetMapping("/page/{page}")
    public Page<Proveedor> listarProveedor(@PathVariable("page") int page){
        return proveedorService.findAll(PageRequest.of(page,10));
    }

    @GetMapping("/")
    public ResponseEntity<?> listarProveedores(){
        return ResponseEntity.ok(proveedorService.obtenerProveedores());
    }

    @PutMapping("/")
    public Proveedor actualizarProveedor(@RequestBody Proveedor proveedor){
        return proveedorService.actualizarProveedor(proveedor);
    }

    @DeleteMapping("/{proveedorId}")
    public void eliminarProveedor(@PathVariable("proveedorId") Long proveedorId){
        proveedorService.eliminarProveedor(proveedorId);
    }
}
