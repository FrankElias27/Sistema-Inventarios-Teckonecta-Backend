package com.example.aos.sistema_aos_spring_boot.Controladores;

import com.example.aos.sistema_aos_spring_boot.Modelo.Ventas;
import com.example.aos.sistema_aos_spring_boot.Modelo.DetalleVenta;
import com.example.aos.sistema_aos_spring_boot.Servicios.VentasService;
import com.example.aos.sistema_aos_spring_boot.Servicios.DetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/detalleventa")
@CrossOrigin("*")
public class DetalleVentaController {

    @Autowired
    private DetalleVentaService detalleVentaService;

    @Autowired
    private VentasService ventaService;

    @PostMapping("/")
    public ResponseEntity<DetalleVenta> guardarDetalleVenta(@RequestBody DetalleVenta detalleVenta){
        return ResponseEntity.ok(detalleVentaService.agregarDetalleVenta(detalleVenta));
    }

    @PutMapping("/")
    public ResponseEntity<DetalleVenta> actualizarDetalleVenta(@RequestBody DetalleVenta detalleVenta){
        return ResponseEntity.ok(detalleVentaService.actualizarDetalleVenta(detalleVenta));
    }

    @GetMapping("/")
    public ResponseEntity<?> listarDetalleVentas(){
        return ResponseEntity.ok(detalleVentaService.obtenerDetalleVentas());
    }

    @GetMapping("/{detalleVentaId}")
    public DetalleVenta listarDetalleVenta(@PathVariable("detalleVentaId") Long detalleVentaId){
        return detalleVentaService.obtenerDetalleVenta(detalleVentaId);
    }

    @DeleteMapping("/{detalleVentaIdId}")
    public void eliminarDetalleVentaId(@PathVariable("detalleVentaIdId") Long detalleVentaId){
        detalleVentaService.eliminarDetalleVenta(detalleVentaId);
    }

    @GetMapping("/venta/{ventaId}")
    public ResponseEntity<?> listarDetalleVentaDelVentas(@PathVariable("ventaId") Long ventaId){
        Ventas ventas = ventaService.obtenerVenta(ventaId);
        Set<DetalleVenta> detalleVentas = ventas.getDetalleVenta();

        List venta = new ArrayList(detalleVentas);

        if (venta.size() > Math.floor(ventas.getNumTotalProducto())) {
            // Convertir a int después de redondear hacia abajo
            int numTotalProducto = (int) Math.floor(ventas.getNumTotalProducto());
            venta = venta.subList(0, numTotalProducto + 1); // Asegúrate de que el índice no exceda el tamaño de la lista
        }

        Collections.shuffle(venta);
        return ResponseEntity.ok(venta);
    }

    @GetMapping("/venta/todos/{ventaId}")
    public ResponseEntity<?> listarDetalleVentaDelVentasComoAdministrador(@PathVariable("ventaId") Long ventaId){
        Ventas venta = new Ventas();
        venta.setVentaId(ventaId);
        Set<DetalleVenta> detalleVentas = detalleVentaService.obtenerDetalleVentaDelVenta(venta);
        return ResponseEntity.ok(detalleVentas);
    }

}
