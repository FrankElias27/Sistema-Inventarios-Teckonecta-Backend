package com.example.aos.sistema_aos_spring_boot.Servicios.impl;

import com.example.aos.sistema_aos_spring_boot.Modelo.Categoria;
import com.example.aos.sistema_aos_spring_boot.Modelo.Cliente;
import com.example.aos.sistema_aos_spring_boot.Modelo.Ventas;
import com.example.aos.sistema_aos_spring_boot.Repositorios.CategoriaRepository;
import com.example.aos.sistema_aos_spring_boot.Repositorios.ClienteRepository;
import com.example.aos.sistema_aos_spring_boot.Servicios.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente agregarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Page<Cliente> findAll(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    @Override
    public Cliente actualizarCliente(Cliente cliente) {
        // Verificar si el ID del cliente es válido y si el cliente existe en la base de datos
        if (cliente.getClienteId() == null || !clienteRepository.existsById(cliente.getClienteId())) {
            throw new EntityNotFoundException("El cliente con ID " + cliente.getClienteId() + " no existe.");
        }

        // Si el cliente existe, proceder con la actualización
        return clienteRepository.save(cliente);
    }

    @Override
    public Set<Cliente> obtenerClientes() {
        return new LinkedHashSet<>(clienteRepository.findAll());
    }

    @Override
    public Cliente obtenerCliente(Long clienteId) {
        return clienteRepository.findById(clienteId).get();
    }


    @Override
    public void eliminarCliente(Long clienteId) {
        if (clienteRepository.existsById(clienteId)) {
            clienteRepository.deleteById(clienteId);
        } else {
            throw new EntityNotFoundException("El cliente con ID " + clienteId + " no existe.");
        }
    }
}
