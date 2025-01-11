package com.example.aos.sistema_aos_spring_boot.Servicios;

import com.example.aos.sistema_aos_spring_boot.Modelo.Inventario;
import com.example.aos.sistema_aos_spring_boot.Modelo.InventarioStock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface InventarioStockService {
    Page<InventarioStock> getStocksByInventarioId(Long inventarioId, Pageable pageable);

    void eliminarInventarioStock(Long stockId);

    InventarioStock agregarStock(InventarioStock inventarioStock);

    InventarioStock actualizarStock(InventarioStock inventarioStock);

    InventarioStock obtenerStock(Long stockId);
}
