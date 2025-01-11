package com.example.aos.sistema_aos_spring_boot.Repositorios;

import com.example.aos.sistema_aos_spring_boot.Modelo.DetalleCompra;
import com.example.aos.sistema_aos_spring_boot.Modelo.DetalleVenta;
import com.example.aos.sistema_aos_spring_boot.Modelo.Ventas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface DetalleVentaRepository extends JpaRepository <DetalleVenta,Long>{

    Set<DetalleVenta> findByVenta(Ventas ventas);

    List<DetalleVenta> findByVentaVentaId(Long ventaId);
}
