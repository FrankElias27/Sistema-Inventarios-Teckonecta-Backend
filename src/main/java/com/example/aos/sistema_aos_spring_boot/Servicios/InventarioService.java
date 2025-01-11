package com.example.aos.sistema_aos_spring_boot.Servicios;

import com.example.aos.sistema_aos_spring_boot.Modelo.Inventario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface InventarioService {

    Inventario agregarInventario(Inventario inventario);

    Inventario actualizarInventario(Inventario inventario);

    Set<Inventario> obtenerInventarios();

    Inventario obtenerInventario(Long inventarioId);

    void eliminarInventario(Long inventarioId);

    Page<Inventario> findAll(Pageable pageable);
}
