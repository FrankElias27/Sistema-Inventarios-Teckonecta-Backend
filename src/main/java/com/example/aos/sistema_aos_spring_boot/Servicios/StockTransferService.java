package com.example.aos.sistema_aos_spring_boot.Servicios;

import com.example.aos.sistema_aos_spring_boot.Modelo.StockTransfer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface StockTransferService {

    Page<StockTransfer> findAll(Pageable pageable);

    StockTransfer agregarTransferencia(StockTransfer stockTransfer);

    StockTransfer actualizarTransferencia(StockTransfer stockTransfer);

    Set<StockTransfer> obtenerTransferencias();

    StockTransfer obtenerTransferencia(Long transferId);

    void eliminarTransferencia(Long transferId);
}
