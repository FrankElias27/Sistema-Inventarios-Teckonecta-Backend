package com.example.aos.sistema_aos_spring_boot.Servicios.impl;


import com.example.aos.sistema_aos_spring_boot.Modelo.Productos;
import com.example.aos.sistema_aos_spring_boot.Modelo.Ventas;
import com.example.aos.sistema_aos_spring_boot.Repositorios.VentasRepository;
import com.example.aos.sistema_aos_spring_boot.Servicios.VentasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class VentasServiceImpl implements VentasService {

    @Autowired
    private VentasRepository ventasRepository;

    @Override
    public Page<Ventas> findAll(Pageable pageable) {
        return ventasRepository.findAll(pageable);
    }

    @Override
    public Ventas agregarVenta(Ventas ventas) {
        return ventasRepository.save(ventas);
    }

    @Override
    public Ventas actualizarVenta(Ventas ventas) {
        return ventasRepository.save(ventas);
    }

    @Override
    public Set<Ventas> obtenerVentas() {
        return new LinkedHashSet<>(ventasRepository.findAll());
    }

    @Override
    public Ventas obtenerVenta(Long ventasId) {
        return ventasRepository.findById(ventasId).get();
    }

    @Override
    public void eliminarVenta(Long ventasId) {
        Ventas venta = new Ventas();
        venta.setVentaId(ventasId);
        ventasRepository.delete(venta);
    }

    @Override
    public List<Ventas> obtenerVentaActivos() {
        return ventasRepository.findByActivo(true);
    }

}
