package com.example.aos.sistema_aos_spring_boot.Servicios;

import com.example.aos.sistema_aos_spring_boot.Modelo.Categoria;
import com.example.aos.sistema_aos_spring_boot.Modelo.Productos;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface ProductosService {

    Productos guardarProducto(Productos producto, MultipartFile imagen);

    Productos actualizarProducto(Productos producto, MultipartFile imagen);

    Page<Productos> findAll(Pageable pageable);

    Productos agregarProductos(Productos productos);

    Set<Productos> obtenerProductos();

    Productos obtenerProductos(Long ProductoId);

    void eliminarProductos(Long ProductoId);

    List<Productos> listarProductosDeUnaCategoria(Categoria categoria);

    Page<Productos> buscarProductosPorNombre(String nombre, Pageable pageable);

    List<Productos> buscarProductosPorNombre(String nombre);




}

