package com.example.aos.sistema_aos_spring_boot.Controladores;


import com.example.aos.sistema_aos_spring_boot.Modelo.Compras;
import com.example.aos.sistema_aos_spring_boot.Modelo.DetalleCompra;
import com.example.aos.sistema_aos_spring_boot.Modelo.DetalleVenta;
import com.example.aos.sistema_aos_spring_boot.Modelo.Ventas;
import com.example.aos.sistema_aos_spring_boot.Servicios.ComprasService;
import com.example.aos.sistema_aos_spring_boot.Servicios.DetalleCompraService;
import com.example.aos.sistema_aos_spring_boot.Servicios.DetalleVentaService;
import com.example.aos.sistema_aos_spring_boot.Servicios.VentasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/detallecompra")
@CrossOrigin("*")
public class DetalleCompraController {

    @Autowired
    private DetalleCompraService detalleCompraService;

    @Autowired
    private ComprasService comprasService;

    @PostMapping("/")
    public ResponseEntity<DetalleCompra> guardarDetalleCompra(@RequestBody DetalleCompra detalleCompra){
        return ResponseEntity.ok(detalleCompraService.agregarDetalleCompra(detalleCompra));
    }

    @PutMapping("/")
    public ResponseEntity<DetalleCompra> actualizarDetalleCompra(@RequestBody DetalleCompra detalleCompra){
        return ResponseEntity.ok(detalleCompraService.actualizarDetalleCompra(detalleCompra));
    }

    @GetMapping("/")
    public ResponseEntity<?> listarDetalleCompras(){
        return ResponseEntity.ok(detalleCompraService.obtenerDetalleCompras());
    }

    @GetMapping("/{detalleCompraId}")
    public DetalleCompra listarDetalleCompra(@PathVariable("detalleCompraId") Long detalleCompraId){
        return detalleCompraService.obtenerDetalleCompra(detalleCompraId);
    }

    @DeleteMapping("/{detalleCompraIdId}")
    public void eliminarDetalleCompraId(@PathVariable("detalleCompraIdId") Long detalleCompraId){
        detalleCompraService.eliminarDetalleCompra(detalleCompraId);
    }

    @GetMapping("/compra/{compraId}")
    public ResponseEntity<?> listarDetalleCompraDelCompras(@PathVariable("compraId") Long compraId){
        Compras compras = comprasService.obtenerCompra(compraId);
        Set<DetalleCompra> detalleCompras = compras.getDetalleCompra();

        List compra = new ArrayList(detalleCompras);

        if(compra.size() > Integer.parseInt(compras.getNumTotaldeProductos())){
            compra = compra.subList(0,Integer.parseInt(compras.getNumTotaldeProductos() + 1));
        }

        Collections.shuffle(compra);
        return ResponseEntity.ok(compra);
    }

    @GetMapping("/compra/todos/{compraId}")
    public ResponseEntity<?> listarDetalleCompraDelComprasComoAdministrador(@PathVariable("compraId") Long compraId){
        Compras compras = new Compras();
        compras.setCompraId(compraId);
        Set<DetalleCompra> detalleCompras = detalleCompraService.obtenerDetalleCompraDelCompra(compras);
        return ResponseEntity.ok(detalleCompras);
    }

}
