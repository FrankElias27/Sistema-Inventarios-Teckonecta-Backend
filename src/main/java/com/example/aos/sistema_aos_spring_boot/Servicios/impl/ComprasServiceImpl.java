package com.example.aos.sistema_aos_spring_boot.Servicios.impl;

import com.example.aos.sistema_aos_spring_boot.Modelo.Compras;
import com.example.aos.sistema_aos_spring_boot.Modelo.Ventas;
import com.example.aos.sistema_aos_spring_boot.Repositorios.ComprasRepository;
import com.example.aos.sistema_aos_spring_boot.Repositorios.VentasRepository;
import com.example.aos.sistema_aos_spring_boot.Servicios.ComprasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class ComprasServiceImpl implements ComprasService {

    @Autowired
    private ComprasRepository comprasRepository;

    @Override
    public Page<Compras> findAll(Pageable pageable) {
        return comprasRepository.findAll(pageable);
    }

    @Override
    public Compras agregarCompra(Compras compras) {
        return comprasRepository.save(compras);
    }

    @Override
    public Compras actualizarCompra(Compras compras) {
        return comprasRepository.save(compras);
    }

    @Override
    public Set<Compras> obtenerCompras() {
        return new LinkedHashSet<>(comprasRepository.findAll());
    }

    @Override
    public Compras obtenerCompra(Long comprasId) {
        return comprasRepository.findById(comprasId).get();
    }

    @Override
    public void eliminarCompra(Long comprasId) {
        Compras compra = new Compras();
        compra.setCompraId(comprasId);
        comprasRepository.delete(compra);
    }

    @Override
    public List<Compras> obtenerComprasActivos() {
        return comprasRepository.findByActivo(true);
    }

}
