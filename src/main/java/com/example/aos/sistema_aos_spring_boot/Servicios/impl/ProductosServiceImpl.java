package com.example.aos.sistema_aos_spring_boot.Servicios.impl;

import com.example.aos.sistema_aos_spring_boot.Exceptions.*;
import com.example.aos.sistema_aos_spring_boot.Modelo.Categoria;
import com.example.aos.sistema_aos_spring_boot.Modelo.Productos;
import com.example.aos.sistema_aos_spring_boot.Modelo.Proveedor;
import com.example.aos.sistema_aos_spring_boot.Repositorios.ProductosRepository;
import com.example.aos.sistema_aos_spring_boot.Servicios.ProductosService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.*;

@Service
public class ProductosServiceImpl implements ProductosService {

    @Autowired
    private ProductosRepository productosRepository;

    @Override
    public Productos guardarProducto(Productos producto, MultipartFile imagen) {
        // Validar el tamaño de la imagen (50 KB)
        long MAX_SIZE = 50 * 1024; // 50 KB
        if (imagen != null && !imagen.isEmpty()) {
            // Validar el tamaño de la imagen
            if (imagen.getSize() > MAX_SIZE) {
                throw new ImageSizeExceededException("El tamaño de la imagen excede el límite permitido de 50KB");
            }

            // Validar que la imagen sea del tipo WebP (opcional, si deseas verificar el formato)
            String contentType = imagen.getContentType();
            if (contentType == null || !contentType.equals("image/webp")) {
                throw new InvalidImageFormatException("La imagen debe estar en formato WebP");
            }

            try {
                // Convertir la imagen a un array de bytes y asignarla al producto
                byte[] imagenBytes = imagen.getBytes();
                producto.setImagen(imagenBytes);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Error al procesar la imagen");
            }
        }

        // Validar que el SKU no exista en la base de datos usando findByCodigo
        Optional<Productos> productoExistente = productosRepository.findByCodigo(producto.getCodigo());
        if (productoExistente.isPresent()) {
            throw new SkuExistenteException("El SKU con código " + producto.getCodigo() + " ya está registrado.");
        }

        // Guardar el producto en la base de datos
        return productosRepository.save(producto);
    }


    @Override
    public Page<Productos> findAll(Pageable pageable) {
        return productosRepository.findAll(pageable);
    }


    @Override
    public Productos agregarProductos(Productos productos) {
        return productosRepository.save(productos);
    }

    @Override
    public Productos actualizarProducto(Productos productos, MultipartFile imagen) {
        // Verificar si el ID del producto es válido y si el producto existe
        if (productos.getProductoId() == null || !productosRepository.existsById(productos.getProductoId())) {
            throw new EntityNotFoundException("El producto con ID " + productos.getProductoId() + " no existe.");
        }

        Productos productoExistentes = productosRepository.findByCodigo(productos.getCodigo()).orElse(null);
        if (productoExistentes != null && !productoExistentes.getProductoId().equals(productos.getProductoId())) {
            throw new ProductoExistenteException("El código " + productos.getCodigo() + " ya está registrado para otro producto.");
        }

        // Obtener el producto existente para preservar datos no incluidos en la solicitud
        Productos productoExistente = productosRepository.findById(productos.getProductoId())
                .orElseThrow(() -> new EntityNotFoundException("El producto con ID " + productos.getProductoId() + " no existe."));

        // Validar y actualizar la imagen si se proporciona
        final long MAX_SIZE = 50 * 1024; // Tamaño máximo de la imagen (50 KB)
        final String FORMATO_PERMITIDO = "image/webp";

        if (imagen != null && !imagen.isEmpty()) {
            if (imagen.getSize() > MAX_SIZE) {
                throw new ImageSizeExceededException("El tamaño de la imagen excede el límite permitido de 50KB");
            }

            if (!FORMATO_PERMITIDO.equals(imagen.getContentType())) {
                throw new InvalidImageFormatException("La imagen debe estar en formato WebP");
            }

            try {
                productos.setImagen(imagen.getBytes());
            } catch (IOException e) {
                throw new ImagenProcesamientoException("Error al procesar la imagen", e);
            }
        } else {
            // Si no se proporciona una nueva imagen, mantener la actual
            productos.setImagen(productoExistente.getImagen());
        }

// Actualizar otros atributos del producto
        productoExistente.setNombre(productos.getNombre());
        productoExistente.setCodigo(productos.getCodigo());
        productoExistente.setDescripcion(productos.getDescripcion());
        productoExistente.setCostoCompraUSD(productos.getCostoCompraUSD());
        productoExistente.setCategoria(productos.getCategoria());
        productoExistente.setImagen(productos.getImagen());

// Guardar el producto actualizado
        return productosRepository.save(productoExistente);
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
        // Verificar si el producto existe en la base de datos
        if (productosRepository.existsById(productoId)) {
            productosRepository.deleteById(productoId);
        } else {
            throw new EntityNotFoundException("El producto con ID " + productoId + " no existe.");
        }
    }

    @Override
    public List<Productos> listarProductosDeUnaCategoria(Categoria categoria) {
        return this.productosRepository.findByCategoria(categoria);
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

