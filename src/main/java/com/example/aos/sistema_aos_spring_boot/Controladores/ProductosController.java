package com.example.aos.sistema_aos_spring_boot.Controladores;

import com.example.aos.sistema_aos_spring_boot.Modelo.Categoria;
import com.example.aos.sistema_aos_spring_boot.Modelo.Ventas;
import com.example.aos.sistema_aos_spring_boot.Modelo.Productos;
import com.example.aos.sistema_aos_spring_boot.Servicios.VentasService;
import com.example.aos.sistema_aos_spring_boot.Servicios.ProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Page<Productos>> listarProductosPage(@PathVariable("page") int page) {

        if (page < 0) {
            return ResponseEntity.badRequest().build();
        }

        try {
            Sort sort = Sort.by(Sort.Order.desc("productoId"));
            PageRequest pageRequest = PageRequest.of(page, 8, sort);
            Page<Productos> eventosPage = productosService.findAll(pageRequest);

            if (eventosPage.isEmpty() && page > 0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return ResponseEntity.ok(eventosPage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
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