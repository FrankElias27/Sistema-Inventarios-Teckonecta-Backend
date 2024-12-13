package com.example.aos.sistema_aos_spring_boot.Servicios.impl;

import com.example.aos.sistema_aos_spring_boot.Modelo.Compras;
import com.example.aos.sistema_aos_spring_boot.Modelo.DetalleCompra;
import com.example.aos.sistema_aos_spring_boot.Modelo.DetalleVenta;
import com.example.aos.sistema_aos_spring_boot.Modelo.Ventas;
import com.example.aos.sistema_aos_spring_boot.Repositorios.DetalleCompraRepository;
import com.example.aos.sistema_aos_spring_boot.Repositorios.DetalleVentaRepository;
import com.example.aos.sistema_aos_spring_boot.Servicios.DetalleCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class DetalleCompraServiceImpl implements DetalleCompraService {

    @Autowired
    private DetalleCompraRepository detalleCompraRepository;

    @Override
    public DetalleCompra agregarDetalleCompra(DetalleCompra detalleCompra) {
        return detalleCompraRepository.save(detalleCompra);
    }

    @Override
    public DetalleCompra actualizarDetalleCompra(DetalleCompra detalleCompra) {
        return detalleCompraRepository.save(detalleCompra);
    }

    @Override
    public Set<DetalleCompra> obtenerDetalleCompras() {
        return new LinkedHashSet<>(detalleCompraRepository.findAll());
    }

    @Override
    public DetalleCompra obtenerDetalleCompra(Long detalleCompraId) {
        return detalleCompraRepository.findById(detalleCompraId).get();
    }

    @Override
    public void eliminarDetalleCompra(Long detalleCompraId) {
        DetalleCompra detalleCompra = new DetalleCompra();
        detalleCompra.setDetalleCompraId(detalleCompraId);
        detalleCompraRepository.delete(detalleCompra);
    }


    @Override
    public Set<DetalleCompra> obtenerDetalleCompraDelCompra(Compras compras) {
        return detalleCompraRepository.findByCompra(compras);
    }

    @Override
    public DetalleCompra listarDetalleCompra(Long detalleCompraId) {
        return this.detalleCompraRepository.getOne(detalleCompraId);
    }
}
