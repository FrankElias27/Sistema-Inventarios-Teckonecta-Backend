package com.example.aos.sistema_aos_spring_boot.Servicios.impl;

import com.example.aos.sistema_aos_spring_boot.Modelo.Cotizacion;
import com.example.aos.sistema_aos_spring_boot.Modelo.DetalleCotizacion;
import com.example.aos.sistema_aos_spring_boot.Modelo.DetalleVenta;
import com.example.aos.sistema_aos_spring_boot.Modelo.Ventas;
import com.example.aos.sistema_aos_spring_boot.Repositorios.DetalleCotizacionRepository;
import com.example.aos.sistema_aos_spring_boot.Repositorios.DetalleVentaRepository;
import com.example.aos.sistema_aos_spring_boot.Servicios.DetalleCotizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class DetalleCotizacionServiceImpl implements DetalleCotizacionService {

    @Autowired
    private DetalleCotizacionRepository detalleCotizacionRepository;

    @Override
    public DetalleCotizacion agregarDetalleCotizacion(DetalleCotizacion detalleCotizacion) {
        return detalleCotizacionRepository.save(detalleCotizacion);
    }

    @Override
    public DetalleCotizacion actualizarDetalleCotizacion(DetalleCotizacion detalleCotizacion) {
        return detalleCotizacionRepository.save(detalleCotizacion);
    }

    @Override
    public Set<DetalleCotizacion> obtenerDetalleCotizaciones() {
        return new LinkedHashSet<>(detalleCotizacionRepository.findAll());
    }

    @Override
    public DetalleCotizacion obtenerDetalleCotizacion(Long detalleCotizacionId) {
        return detalleCotizacionRepository.findById(detalleCotizacionId).get();
    }

    @Override
    public void eliminarDetalleCotizacion(Long detalleCotizacionId) {
        DetalleCotizacion detalleCotizacion = new DetalleCotizacion();
        detalleCotizacion.setDetalleCotizacionId(detalleCotizacionId);
        detalleCotizacionRepository.delete(detalleCotizacion);
    }


    @Override
    public Set<DetalleCotizacion> obtenerDetalleCotizacionDelCotizacion(Cotizacion cotizacion) {
        return detalleCotizacionRepository.findByCotizacion(cotizacion);
    }

    @Override
    public DetalleCotizacion listarDetalleCotizacion(Long detalleCotizacionId) {
        return this.detalleCotizacionRepository.getOne(detalleCotizacionId);
    }
}
