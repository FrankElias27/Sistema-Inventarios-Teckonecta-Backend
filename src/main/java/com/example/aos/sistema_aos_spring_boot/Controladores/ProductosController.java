package com.example.aos.sistema_aos_spring_boot.Controladores;

import com.example.aos.sistema_aos_spring_boot.Modelo.Categoria;
import com.example.aos.sistema_aos_spring_boot.Modelo.Ventas;
import com.example.aos.sistema_aos_spring_boot.Modelo.Productos;
import com.example.aos.sistema_aos_spring_boot.Servicios.VentasService;
import com.example.aos.sistema_aos_spring_boot.Servicios.ProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin("*")
public class ProductosController {

    @Autowired
    private ProductosService productosService;

    @PostMapping("/")
    public ResponseEntity<Productos> guardarProductos(@RequestBody Productos productos){
        return ResponseEntity.ok(productosService.agregarProductos(productos));
    }

    @PutMapping("/")
    public ResponseEntity<Productos> actualizarProductos(@RequestBody Productos productos){
        return ResponseEntity.ok(productosService.actualizarProductos(productos));
    }

    @GetMapping("/")
    public ResponseEntity<?> listarProductos(){
        return ResponseEntity.ok(productosService.obtenerProductos());
    }

    @GetMapping("/page/{page}")
    public Page<Productos> listarProducto(@PathVariable("page") int page){
        return productosService.findAll(PageRequest.of(page,10));
    }

    @GetMapping("/{productoId}")
    public Productos listarProductos(@PathVariable("productoId") Long productoId){
        return productosService.obtenerProductos(productoId);
    }

    @DeleteMapping("/{productoId}")
    public void eliminarProductos(@PathVariable("productoId") Long productoId){
        productosService.eliminarProductos(productoId);
    }

    @GetMapping("/categoria/{categoriaId}")
    public List<Productos> listarProductosDeUnaCategoria(@PathVariable("categoriaId") Long categoriaId){
        Categoria categoria = new Categoria();
        categoria.setCategoriaId(categoriaId);
        return productosService.listarProductosDeUnaCategoria(categoria);
    }

    @GetMapping("/activo")
    public List<Productos> listarProductosActivos(){
        return productosService.obtenerProductosActivos();
    }

    @GetMapping("/categoria/activo/{categoriaId}")
    public List<Productos> listarProductosActivosDeUnaCategoria(@PathVariable("categoriaId") Long categoriaId){
        Categoria categoria = new Categoria();
        categoria.setCategoriaId(categoriaId);
        return productosService.obtenerProductosActivosDeUnaCategoria(categoria);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<Productos>> buscarProductosPorNombre(
            @RequestParam("nombre") String nombre,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Productos> productosEncontrados = productosService.buscarProductosPorNombre(nombre, pageable);
        return ResponseEntity.ok(productosEncontrados);
    }

    @GetMapping("/buscarproducto")
    public ResponseEntity<List<Productos>> buscarProductosPorNombres(
            @RequestParam("nombre") String nombre) {

        List<Productos> productosEncontrados = productosService.buscarProductosPorNombre(nombre);
        return ResponseEntity.ok(productosEncontrados);
    }




}