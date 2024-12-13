package com.example.aos.sistema_aos_spring_boot.Repositorios;

import com.example.aos.sistema_aos_spring_boot.Modelo.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface DetalleServiciosRepository extends JpaRepository<DetalleServicios, Long> {

    Set<DetalleServicios> findByCotizacion(Cotizacion cotizacion);
}
