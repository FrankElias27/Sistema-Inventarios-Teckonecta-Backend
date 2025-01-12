package com.example.aos.sistema_aos_spring_boot.Repositorios;

import com.example.aos.sistema_aos_spring_boot.Modelo.Servicio;
import com.example.aos.sistema_aos_spring_boot.Modelo.StockTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockTransferRepository extends JpaRepository<StockTransfer, Long> {
}
