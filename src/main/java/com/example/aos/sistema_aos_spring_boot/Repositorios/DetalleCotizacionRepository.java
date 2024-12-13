package com.example.aos.sistema_aos_spring_boot.Repositorios;

import com.example.aos.sistema_aos_spring_boot.Modelo.Cotizacion;
import com.example.aos.sistema_aos_spring_boot.Modelo.DetalleCotizacion;
import com.example.aos.sistema_aos_spring_boot.Modelo.DetalleVenta;
import com.example.aos.sistema_aos_spring_boot.Modelo.Ventas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface DetalleCotizacionRepository extends JpaRepository<DetalleCotizacion, Long> {
    Set<DetalleCotizacion> findByCotizacion(Cotizacion cotizacion);
}
