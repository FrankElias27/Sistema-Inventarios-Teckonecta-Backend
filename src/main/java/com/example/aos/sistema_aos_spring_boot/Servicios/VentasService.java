package com.example.aos.sistema_aos_spring_boot.Servicios;

import com.example.aos.sistema_aos_spring_boot.Modelo.Productos;
import com.example.aos.sistema_aos_spring_boot.Modelo.Ventas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface VentasService {

    Page<Ventas> findAll(Pageable pageable);

    Ventas agregarVenta(Ventas ventas);

    Ventas actualizarVenta(Ventas ventas);

    Set<Ventas>obtenerVentas();

    Ventas obtenerVenta(Long ventasId);

    void eliminarVenta(Long ventasId);

    List<Ventas> obtenerVentaActivos();

}
