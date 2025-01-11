package com.example.aos.sistema_aos_spring_boot.Servicios.impl;

import com.example.aos.sistema_aos_spring_boot.Exceptions.AsesorVentaExistenteException;
import com.example.aos.sistema_aos_spring_boot.Modelo.AsesorVenta;
import com.example.aos.sistema_aos_spring_boot.Modelo.Compras;
import com.example.aos.sistema_aos_spring_boot.Modelo.DetalleCompra;
import com.example.aos.sistema_aos_spring_boot.Modelo.Proveedor;
import com.example.aos.sistema_aos_spring_boot.Repositorios.AsesorVentaRepository;
import com.example.aos.sistema_aos_spring_boot.Servicios.AsesorVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityNotFoundException;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AsesorVentaServiceImpl implements AsesorVentaService {

    @Autowired
    private AsesorVentaRepository asesorVentaRepository;

    @Override
    public AsesorVenta agregarAsesorVenta(AsesorVenta asesorVenta) {
        Optional<AsesorVenta> asesorVentaExistente = asesorVentaRepository.findByTelefono(asesorVenta.getTelefono());

        if (asesorVentaExistente.isPresent()) {
            throw new AsesorVentaExistenteException("El Telefono del Asesor ya está registrado en la base de datos.");
        }

        return asesorVentaRepository.save(asesorVenta);
    }

    @Override
    public Page<AsesorVenta> findAll(Pageable pageable) {
        return asesorVentaRepository.findAll(pageable);
    }

    @Override
    public AsesorVenta actualizarAsesorVenta(AsesorVenta asesorVenta) {
        // Verificar si el ID del asesor de ventas es válido y si el asesor existe en la base de datos
        if (asesorVenta.getAsesorId() == null || !asesorVentaRepository.existsById(asesorVenta.getAsesorId())) {
            throw new EntityNotFoundException("El asesor de ventas con ID " + asesorVenta.getAsesorId() + " no existe.");
        }

        AsesorVenta asesorVentaExistente = asesorVentaRepository.findByTelefono(asesorVenta.getTelefono()).orElse(null);
        if (asesorVentaExistente != null && !asesorVentaExistente.getAsesorId().equals(asesorVenta.getAsesorId())) {
            throw new AsesorVentaExistenteException("El Telefono " + asesorVenta.getTelefono() + " ya está registrado para otro asesor de ventas.");
        }

        // Si el asesor existe, proceder con la actualización
        return asesorVentaRepository.save(asesorVenta);
    }

    @Override
    public Set<AsesorVenta> obtenerAsesoresVenta() {
        return new LinkedHashSet<>(asesorVentaRepository.findAll());
    }

    @Override
    public AsesorVenta obtenerAsesorVenta(Long asesorVentaId) {
        return asesorVentaRepository.findById(asesorVentaId).get();
    }

    @Override
    public void eliminarAsesorVenta(Long asesorVentaId) {
        if (asesorVentaRepository.existsById(asesorVentaId)) {
            asesorVentaRepository.deleteById(asesorVentaId);
        } else {
            throw new EntityNotFoundException("El asesor de ventas con ID " + asesorVentaId + " no existe.");
        }
    }

    @Override
    public Set<AsesorVenta> obtenerAsesorVentaDelProveedor(Proveedor proveedor) {
        return asesorVentaRepository.findByProveedor(proveedor);
    }

}
