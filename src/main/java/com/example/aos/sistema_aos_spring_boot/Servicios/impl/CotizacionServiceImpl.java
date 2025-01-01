package com.example.aos.sistema_aos_spring_boot.Servicios.impl;

import com.example.aos.sistema_aos_spring_boot.Modelo.Compras;
import com.example.aos.sistema_aos_spring_boot.Modelo.Cotizacion;
import com.example.aos.sistema_aos_spring_boot.Modelo.Ventas;
import com.example.aos.sistema_aos_spring_boot.Repositorios.CotizacionRepository;
import com.example.aos.sistema_aos_spring_boot.Repositorios.VentasRepository;
import com.example.aos.sistema_aos_spring_boot.Servicios.CotizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class CotizacionServiceImpl implements CotizacionService {

    @Autowired
    private CotizacionRepository cotizacionRepository;

    @Override
    public Page<Cotizacion> findAll(Pageable pageable) {
        return cotizacionRepository.findAll(pageable);
    }

    @Override
    public Cotizacion agregarCotizacion(Cotizacion cotizacion) {
        return cotizacionRepository.save(cotizacion);
    }


    @Override
    public Cotizacion actualizarCotizacion(Cotizacion cotizacion) {
        // Verificar si el ID de la compra es válido y si la compra existe en la base de datos
        if (cotizacion.getCotizacionId() == null || !cotizacionRepository.existsById(cotizacion.getCotizacionId())) {
            throw new EntityNotFoundException("La compra con ID " + cotizacion.getCotizacionId() + " no existe.");
        }
        // Si la compra existe, proceder con la actualización
        return cotizacionRepository.save(cotizacion);
    }

    @Override
    public Set<Cotizacion> obtenerCotizaciones() {
        return new LinkedHashSet<>(cotizacionRepository.findAll());
    }

    @Override
    public Cotizacion obtenerCotizacion(Long cotizacionId) {
        return cotizacionRepository.findById(cotizacionId).get();
    }

    @Override
    public void eliminarCotizacion(Long cotizacionId) {
        if (cotizacionRepository.existsById(cotizacionId)) {
            cotizacionRepository.deleteById(cotizacionId);
        } else {
            throw new EntityNotFoundException("La compra con ID " + cotizacionId + " no existe.");
        }
    }

}
