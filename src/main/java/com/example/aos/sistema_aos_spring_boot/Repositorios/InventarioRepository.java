package com.example.aos.sistema_aos_spring_boot.Repositorios;

import com.example.aos.sistema_aos_spring_boot.Modelo.DetalleVenta;
import com.example.aos.sistema_aos_spring_boot.Modelo.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventarioRepository extends JpaRepository<Inventario,Long>{
    Optional<Inventario> findById(Long id);
}
