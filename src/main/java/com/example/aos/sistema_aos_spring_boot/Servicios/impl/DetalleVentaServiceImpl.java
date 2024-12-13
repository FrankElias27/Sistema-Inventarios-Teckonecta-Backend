package com.example.aos.sistema_aos_spring_boot.Servicios.impl;

import com.example.aos.sistema_aos_spring_boot.Modelo.DetalleVenta;
import com.example.aos.sistema_aos_spring_boot.Modelo.Ventas;
import com.example.aos.sistema_aos_spring_boot.Repositorios.DetalleVentaRepository;
import com.example.aos.sistema_aos_spring_boot.Servicios.DetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class DetalleVentaServiceImpl implements DetalleVentaService {
    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Override
    public DetalleVenta agregarDetalleVenta(DetalleVenta detalleVenta) {
        return detalleVentaRepository.save(detalleVenta);
    }

    @Override
    public DetalleVenta actualizarDetalleVenta(DetalleVenta detalleVenta) {
        return detalleVentaRepository.save(detalleVenta);
    }

    @Override
    public Set<DetalleVenta> obtenerDetalleVentas() {
        return new LinkedHashSet<>(detalleVentaRepository.findAll());
    }

    @Override
    public DetalleVenta obtenerDetalleVenta(Long detalleVentaId) {
        return detalleVentaRepository.findById(detalleVentaId).get();
    }

    @Override
    public void eliminarDetalleVenta(Long detalleVentaId) {
        DetalleVenta detalleVenta = new DetalleVenta();
        detalleVenta.setDetalleVentaId(detalleVentaId);
        detalleVentaRepository.delete(detalleVenta);
    }


    @Override
    public Set<DetalleVenta> obtenerDetalleVentaDelVenta(Ventas ventas) {
        return detalleVentaRepository.findByVenta(ventas);
    }

    @Override
    public DetalleVenta listarDetalleVenta(Long detalleVentaId) {
        return this.detalleVentaRepository.getOne(detalleVentaId);
    }

}
