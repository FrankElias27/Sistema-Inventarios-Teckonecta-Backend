package com.example.aos.sistema_aos_spring_boot.Servicios.impl;

import com.example.aos.sistema_aos_spring_boot.Modelo.Inventario;
import com.example.aos.sistema_aos_spring_boot.Repositorios.InventarioRepository;
import com.example.aos.sistema_aos_spring_boot.Servicios.InventarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class InventarioServiceImpl implements InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Override
    public Inventario agregarInventario(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    @Override
    public Page<Inventario> findAll(Pageable pageable) {
        return inventarioRepository.findAll(pageable);
    }

    @Override
    public Inventario actualizarInventario(Inventario inventario) {
        // Verificar si el ID del inventario es válido y si el inventario existe
        if (inventario.getInventarioId() == null || !inventarioRepository.existsById(inventario.getInventarioId())) {
            throw new EntityNotFoundException("El inventario con ID " + inventario.getInventarioId() + " no existe.");
        }

        return inventarioRepository.save(inventario);
    }


    @Override
    public Set<Inventario> obtenerInventarios() {
        return new LinkedHashSet<>(inventarioRepository.findAll());
    }

    @Override
    public Inventario obtenerInventario(Long inventarioId) {
        return inventarioRepository.findById(inventarioId).get();
    }

    // Método para eliminar un inventario
    @Override
    public void eliminarInventario(Long inventarioId) {
        if (inventarioRepository.existsById(inventarioId)) {
            inventarioRepository.deleteById(inventarioId);
        } else {
            throw new EntityNotFoundException("El inventario con ID " + inventarioId + " no existe.");
        }
    }
}
