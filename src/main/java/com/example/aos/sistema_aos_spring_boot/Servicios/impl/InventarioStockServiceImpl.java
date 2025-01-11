package com.example.aos.sistema_aos_spring_boot.Servicios.impl;

import com.example.aos.sistema_aos_spring_boot.Modelo.Inventario;
import com.example.aos.sistema_aos_spring_boot.Modelo.InventarioStock;
import com.example.aos.sistema_aos_spring_boot.Repositorios.InventarioRepository;
import com.example.aos.sistema_aos_spring_boot.Repositorios.InventarioStockRepository;
import com.example.aos.sistema_aos_spring_boot.Servicios.InventarioStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class InventarioStockServiceImpl implements InventarioStockService {

    @Autowired
    private InventarioStockRepository inventarioStockRepository;

    @Override
    public Page<InventarioStock> getStocksByInventarioId(Long inventarioId, Pageable pageable) {
        return inventarioStockRepository.findByInventarioInventarioId(inventarioId, pageable);
    }


    @Override
    public void eliminarInventarioStock(Long stockId) {
        if (inventarioStockRepository.existsById(stockId)) {
            inventarioStockRepository.deleteById(stockId);
        } else {
            throw new EntityNotFoundException("El Inventario Stock con ID " + stockId + " no existe.");
        }
    }

    @Override
    public InventarioStock agregarStock(InventarioStock inventarioStock) {
        return inventarioStockRepository.save(inventarioStock);
    }

    @Override
    public InventarioStock actualizarStock(InventarioStock inventarioStock) {

        if (inventarioStock.getStockId() == null || !inventarioStockRepository.existsById(inventarioStock.getStockId())) {
            throw new EntityNotFoundException("El inventarioStock con ID " + inventarioStock.getStockId() + " no existe.");
        }

        return inventarioStockRepository.save(inventarioStock);
    }

    @Override
    public InventarioStock obtenerStock(Long stockId) {
        return inventarioStockRepository.findById(stockId).get();
    }
}
