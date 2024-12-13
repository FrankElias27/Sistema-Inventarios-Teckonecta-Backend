package com.example.aos.sistema_aos_spring_boot.Servicios;

import com.example.aos.sistema_aos_spring_boot.Modelo.Cotizacion;
import com.example.aos.sistema_aos_spring_boot.Modelo.Ventas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface CotizacionService {

    Page<Cotizacion> findAll(Pageable pageable);

    Cotizacion agregarCotizacion(Cotizacion cotizacion);

    Cotizacion actualizarCotizacion(Cotizacion cotizacion);

    Set<Cotizacion> obtenerCotizaciones();

    Cotizacion obtenerCotizacion(Long cotizacionId);

    void eliminarCotizacion(Long cotizacionId);

    List<Cotizacion> obtenerCotizacionActivos();
}
