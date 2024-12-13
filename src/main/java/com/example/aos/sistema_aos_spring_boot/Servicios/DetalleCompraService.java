package com.example.aos.sistema_aos_spring_boot.Servicios;

import com.example.aos.sistema_aos_spring_boot.Modelo.Compras;
import com.example.aos.sistema_aos_spring_boot.Modelo.DetalleCompra;
import com.example.aos.sistema_aos_spring_boot.Modelo.Ventas;

import java.util.Set;

public interface DetalleCompraService {

    DetalleCompra agregarDetalleCompra(DetalleCompra detalleCompra);

    DetalleCompra actualizarDetalleCompra(DetalleCompra detalleCompra);

    Set<DetalleCompra> obtenerDetalleCompras();

    DetalleCompra obtenerDetalleCompra(Long detalleCompraId);

    void eliminarDetalleCompra(Long detalleCompraId);

    Set<DetalleCompra> obtenerDetalleCompraDelCompra(Compras compras);

    DetalleCompra listarDetalleCompra(Long detalleCompraId);
}
