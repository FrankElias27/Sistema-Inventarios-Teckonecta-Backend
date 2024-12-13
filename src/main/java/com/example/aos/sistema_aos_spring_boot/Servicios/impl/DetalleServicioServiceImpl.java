package com.example.aos.sistema_aos_spring_boot.Servicios.impl;

import com.example.aos.sistema_aos_spring_boot.Modelo.*;
import com.example.aos.sistema_aos_spring_boot.Repositorios.DetalleServiciosRepository;
import com.example.aos.sistema_aos_spring_boot.Repositorios.DetalleVentaRepository;
import com.example.aos.sistema_aos_spring_boot.Servicios.DetalleServiciosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class DetalleServicioServiceImpl implements DetalleServiciosService {

    @Autowired
    private DetalleServiciosRepository detalleServiciosRepository;

    @Override
    public DetalleServicios agregarDetalleServicios(DetalleServicios detalleServicios) {
        return detalleServiciosRepository.save(detalleServicios);
    }

    @Override
    public DetalleServicios actualizarDetalleServicios(DetalleServicios detalleServicios) {
        return detalleServiciosRepository.save(detalleServicios);
    }

    @Override
    public Set<DetalleServicios> obtenerDetalleServicios() {
        return new LinkedHashSet<>(detalleServiciosRepository.findAll());
    }

    @Override
    public DetalleServicios obtenerDetalleServicio(Long detalleServicioId) {
        return detalleServiciosRepository.findById(detalleServicioId).get();
    }

    @Override
    public void eliminarDetalleServicios(Long detalleServicioId) {
        DetalleServicios detalleServicios = new DetalleServicios();
        detalleServicios.setDetalleServiciosId(detalleServicioId);
        detalleServiciosRepository.delete(detalleServicios);
    }

    @Override
    public Set<DetalleServicios> obtenerDetalleServiciosDelCotizacion(Cotizacion cotizacion) {
        return detalleServiciosRepository.findByCotizacion(cotizacion);
    }


}
