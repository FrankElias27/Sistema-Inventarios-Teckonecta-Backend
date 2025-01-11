package com.example.aos.sistema_aos_spring_boot.Repositorios;

import com.example.aos.sistema_aos_spring_boot.Modelo.InventarioStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InventarioStockRepository extends JpaRepository<InventarioStock, Long> {

    InventarioStock findByProductoProductoIdAndInventarioInventarioId(Long productoId, Long inventarioId);

    Page<InventarioStock> findByInventarioInventarioId(Long inventarioId, Pageable pageable);
}
