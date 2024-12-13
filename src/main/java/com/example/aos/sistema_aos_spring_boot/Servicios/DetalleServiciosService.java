package com.example.aos.sistema_aos_spring_boot.Servicios;

import com.example.aos.sistema_aos_spring_boot.Modelo.*;

import java.util.Set;

public interface DetalleServiciosService {

    DetalleServicios agregarDetalleServicios(DetalleServicios detalleServicios);

    DetalleServicios actualizarDetalleServicios(DetalleServicios detalleServicios);

    Set<DetalleServicios> obtenerDetalleServicios();

    DetalleServicios obtenerDetalleServicio(Long detalleServicioId);

    void eliminarDetalleServicios(Long detalleServicioId);

    Set<DetalleServicios> obtenerDetalleServiciosDelCotizacion(Cotizacion cotizacion);

}
