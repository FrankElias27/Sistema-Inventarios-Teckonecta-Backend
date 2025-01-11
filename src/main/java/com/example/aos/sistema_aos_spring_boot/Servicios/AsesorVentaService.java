package com.example.aos.sistema_aos_spring_boot.Servicios;

import com.example.aos.sistema_aos_spring_boot.Modelo.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface AsesorVentaService {

    AsesorVenta agregarAsesorVenta(AsesorVenta asesorVenta);

    AsesorVenta actualizarAsesorVenta(AsesorVenta asesorVenta);

    Set<AsesorVenta> obtenerAsesoresVenta();

    AsesorVenta obtenerAsesorVenta(Long asesorVentaId);

    void eliminarAsesorVenta(Long asesorVentaId);

    Set<AsesorVenta> obtenerAsesorVentaDelProveedor(Proveedor proveedor);

    Page<AsesorVenta> findAll(Pageable pageable);
}
