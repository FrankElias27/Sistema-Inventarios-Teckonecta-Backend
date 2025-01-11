package com.example.aos.sistema_aos_spring_boot.Servicios;

import com.example.aos.sistema_aos_spring_boot.Modelo.Compras;
import com.example.aos.sistema_aos_spring_boot.Modelo.Ventas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface ComprasService {

    Page<Compras> findAll(Pageable pageable);

    Compras agregarCompra(Compras compras);

    Compras actualizarCompra(Compras compras);

    Set<Compras> obtenerCompras();

    Compras obtenerCompra(Long comprasId);

    void eliminarCompra(Long comprasId);

    void procesarCompra(Long compraId, Long inventarioId);

}
