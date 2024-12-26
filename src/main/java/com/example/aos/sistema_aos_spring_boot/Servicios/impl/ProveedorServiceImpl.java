package com.example.aos.sistema_aos_spring_boot.Servicios.impl;

import com.example.aos.sistema_aos_spring_boot.Modelo.Cliente;
import com.example.aos.sistema_aos_spring_boot.Modelo.Proveedor;
import com.example.aos.sistema_aos_spring_boot.Repositorios.ClienteRepository;
import com.example.aos.sistema_aos_spring_boot.Repositorios.ProveedorRepository;
import com.example.aos.sistema_aos_spring_boot.Servicios.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public Proveedor agregarProveedor(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    @Override
    public Page<Proveedor> findAll(Pageable pageable) {
        return proveedorRepository.findAll(pageable);
    }

    @Override
    public Proveedor actualizarProveedor(Proveedor proveedor) {
        if (proveedor.getProveedorId() == null || !proveedorRepository.existsById(proveedor.getProveedorId())) {
            throw new EntityNotFoundException("El proveedor con ID " + proveedor.getProveedorId() + " no existe.");
        }
        return proveedorRepository.save(proveedor);
    }

    @Override
    public Set<Proveedor> obtenerProveedores() {
        return new LinkedHashSet<>(proveedorRepository.findAll());
    }

    @Override
    public Proveedor obtenerProveedor(Long proveedorId) {
        return proveedorRepository.findById(proveedorId).get();
    }

    @Override
    public void eliminarProveedor(Long proveedorId) {
        if (proveedorRepository.existsById(proveedorId)) {
            proveedorRepository.deleteById(proveedorId);
        } else {
            throw new EntityNotFoundException("El proveedor con ID " + proveedorId + " no existe.");
        }
    }
}
