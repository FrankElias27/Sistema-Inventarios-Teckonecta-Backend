package com.example.aos.sistema_aos_spring_boot.Servicios;

import com.example.aos.sistema_aos_spring_boot.Modelo.Categoria;
import com.example.aos.sistema_aos_spring_boot.Modelo.Cliente;
import com.example.aos.sistema_aos_spring_boot.Modelo.Ventas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface ClienteService {
    Cliente agregarCliente(Cliente cliente);

    Cliente actualizarCliente(Cliente cliente);

    Set<Cliente> obtenerClientes();

    Cliente obtenerCliente(Long clienteId);

    void eliminarCliente(Long clienteId);

    Page<Cliente> findAll(Pageable pageable);
}