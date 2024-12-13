package com.example.aos.sistema_aos_spring_boot.Servicios.impl;

import com.example.aos.sistema_aos_spring_boot.Modelo.Categoria;
import com.example.aos.sistema_aos_spring_boot.Modelo.Productos;
import com.example.aos.sistema_aos_spring_boot.Repositorios.ProductosRepository;
import com.example.aos.sistema_aos_spring_boot.Servicios.ProductosService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductosServiceImpl implements ProductosService {

    @Autowired
    private ProductosRepository productosRepository;

    @Override
    public Page<Productos> findAll(Pageable pageable) {
        return productosRepository.findAll(pageable);
    }

    @Override
    public Productos agregarProductos(Productos productos) {
        return productosRepository.save(productos);
    }

    @Override
    public Productos actualizarProductos(Productos productos) {
        return productosRepository.save(productos);
    }

    @Override
    public Set<Productos> obtenerProductos() {
        return new LinkedHashSet<>(productosRepository.findAll());
    }

    @Override
    public Productos obtenerProductos(Long productoId) {
        return productosRepository.findById(productoId).get();
    }

    @Override
    public void eliminarProductos(Long productoId) {
        Productos productos = new Productos();
        productos.setProductoId(productoId);
        productosRepository.delete(productos);
    }

    @Override
    public List<Productos> listarProductosDeUnaCategoria(Categoria categoria) {
        return this.productosRepository.findByCategoria(categoria);
    }

    @Override
    public List<Productos> obtenerProductosActivos() {
        return productosRepository.findByActivo(true);
    }

    @Override
    public List<Productos> obtenerProductosActivosDeUnaCategoria(Categoria categoria) {
        return productosRepository.findByCategoriaAndActivo(categoria,true);
    }

    @Override
    public Page<Productos> buscarProductosPorNombre(String nombre, Pageable pageable) {
        return productosRepository.findByNombreContaining(nombre,pageable);
    }

    @Override
    public List<Productos> buscarProductosPorNombre(String nombre) {
        return productosRepository.findByNombreContainingIgnoreCase(nombre);
    }


}

