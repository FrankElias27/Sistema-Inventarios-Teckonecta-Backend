package com.example.aos.sistema_aos_spring_boot.Servicios;

import com.example.aos.sistema_aos_spring_boot.Modelo.DetalleVenta;
import com.example.aos.sistema_aos_spring_boot.Modelo.Ventas;

import java.util.Set;

public interface DetalleVentaService {

    DetalleVenta agregarDetalleVenta(DetalleVenta detalleVenta);

    DetalleVenta actualizarDetalleVenta(DetalleVenta detalleVenta);

    Set<DetalleVenta> obtenerDetalleVentas();

    DetalleVenta obtenerDetalleVenta(Long detalleVentaId);

    void eliminarDetalleVenta(Long detalleVentaId);

    Set<DetalleVenta> obtenerDetalleVentaDelVenta(Ventas ventas);

    DetalleVenta listarDetalleVenta(Long detalleVentaId);
}
