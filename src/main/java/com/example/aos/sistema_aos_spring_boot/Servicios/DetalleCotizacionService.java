package com.example.aos.sistema_aos_spring_boot.Servicios;

import com.example.aos.sistema_aos_spring_boot.Modelo.Cotizacion;
import com.example.aos.sistema_aos_spring_boot.Modelo.DetalleCotizacion;
import com.example.aos.sistema_aos_spring_boot.Modelo.DetalleVenta;
import com.example.aos.sistema_aos_spring_boot.Modelo.Ventas;

import java.util.Set;

public interface DetalleCotizacionService {

    DetalleCotizacion agregarDetalleCotizacion(DetalleCotizacion detalleCotizacion);

    DetalleCotizacion actualizarDetalleCotizacion(DetalleCotizacion detalleCotizacion);

    Set<DetalleCotizacion> obtenerDetalleCotizaciones();

    DetalleCotizacion obtenerDetalleCotizacion(Long detalleCotizacionId);

    void eliminarDetalleCotizacion(Long detalleCotizacionId);

    Set<DetalleCotizacion> obtenerDetalleCotizacionDelCotizacion(Cotizacion cotizacion);

    DetalleCotizacion listarDetalleCotizacion(Long detalleCotizacionId);
}
