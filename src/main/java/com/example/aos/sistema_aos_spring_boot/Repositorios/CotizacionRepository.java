package com.example.aos.sistema_aos_spring_boot.Repositorios;

import com.example.aos.sistema_aos_spring_boot.Modelo.Cotizacion;
import com.example.aos.sistema_aos_spring_boot.Modelo.Ventas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CotizacionRepository extends JpaRepository<Cotizacion, Long> {


}
