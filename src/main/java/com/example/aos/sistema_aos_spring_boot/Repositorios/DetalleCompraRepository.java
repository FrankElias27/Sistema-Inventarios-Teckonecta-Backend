package com.example.aos.sistema_aos_spring_boot.Repositorios;

import com.example.aos.sistema_aos_spring_boot.Modelo.Compras;
import com.example.aos.sistema_aos_spring_boot.Modelo.DetalleCompra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface DetalleCompraRepository extends JpaRepository<DetalleCompra,Long> {
    Set<DetalleCompra> findByCompra(Compras compras);
}
