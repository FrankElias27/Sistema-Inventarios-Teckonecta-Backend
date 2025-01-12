package com.example.aos.sistema_aos_spring_boot.Servicios.impl;

import com.example.aos.sistema_aos_spring_boot.Modelo.StockTransfer;
import com.example.aos.sistema_aos_spring_boot.Modelo.Ventas;
import com.example.aos.sistema_aos_spring_boot.Repositorios.StockTransferRepository;
import com.example.aos.sistema_aos_spring_boot.Repositorios.VentasRepository;
import com.example.aos.sistema_aos_spring_boot.Servicios.StockTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class StockTransferServiceImpl implements StockTransferService {

    @Autowired
    private StockTransferRepository stockTransferRepository;


    @Override
    public Page<StockTransfer> findAll(Pageable pageable) {
        return stockTransferRepository.findAll(pageable);
    }

    @Override
    public StockTransfer agregarTransferencia(StockTransfer stockTransfer) {
        return stockTransferRepository.save(stockTransfer);
    }

    @Override
    public StockTransfer actualizarTransferencia(StockTransfer stockTransfer) {
        // Verificar si el ID de la transferencia es válido y si la transferencia existe en la base de datos
        if (stockTransfer.getTransferId() == null || !stockTransferRepository.existsById(stockTransfer.getTransferId())) {
            throw new EntityNotFoundException("La transferencia con ID " + stockTransfer.getTransferId() + " no existe.");
        }
        // Si la transferencia existe, proceder con la actualización
        return stockTransferRepository.save(stockTransfer);
    }

    @Override
    public Set<StockTransfer> obtenerTransferencias() {
        return new LinkedHashSet<>(stockTransferRepository.findAll());
    }

    @Override
    public StockTransfer obtenerTransferencia(Long transferId) {
        return stockTransferRepository.findById(transferId).get();
    }

    @Override
    public void eliminarTransferencia(Long transferId) {
        if (stockTransferRepository.existsById(transferId)) {
            stockTransferRepository.deleteById(transferId);
        } else {
            throw new EntityNotFoundException("La transferencia con ID " + transferId + " no existe.");
        }
    }
}
