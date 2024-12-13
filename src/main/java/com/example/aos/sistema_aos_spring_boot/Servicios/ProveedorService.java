package com.example.aos.sistema_aos_spring_boot.Servicios;

import com.example.aos.sistema_aos_spring_boot.Modelo.Proveedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface ProveedorService {

    Proveedor agregarProveedor(Proveedor proveedor);

    Proveedor actualizarProveedor(Proveedor proveedor);

    Set<Proveedor> obtenerProveedores();

    Proveedor obtenerProveedor(Long proveedorId);

    void eliminarProveedor(Long proveedorId);

    Page<Proveedor> findAll(Pageable pageable);
}
