package com.example.aos.sistema_aos_spring_boot.Repositorios;

import com.example.aos.sistema_aos_spring_boot.Modelo.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface AsesorVentaRepository extends JpaRepository<AsesorVenta,Long> {

    Optional<AsesorVenta> findByTelefono(String telefono);

    Set<AsesorVenta> findByProveedor(Proveedor proveedor);
}
