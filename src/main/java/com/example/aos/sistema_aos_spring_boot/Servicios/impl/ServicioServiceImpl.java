package com.example.aos.sistema_aos_spring_boot.Servicios.impl;

import com.example.aos.sistema_aos_spring_boot.Modelo.Categoria;
import com.example.aos.sistema_aos_spring_boot.Modelo.Cliente;
import com.example.aos.sistema_aos_spring_boot.Modelo.Servicio;
import com.example.aos.sistema_aos_spring_boot.Repositorios.CategoriaRepository;
import com.example.aos.sistema_aos_spring_boot.Repositorios.ServiciosRepository;
import com.example.aos.sistema_aos_spring_boot.Servicios.ServiciosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class ServicioServiceImpl implements ServiciosService {
    @Autowired
    private ServiciosRepository serviciosRepository;

    @Override
    public Servicio agregarServicio(Servicio servicio) {
        return serviciosRepository.save(servicio);
    }

    @Override
    public Page<Servicio> findAll(Pageable pageable) {
        return serviciosRepository.findAll(pageable);
    }

    @Override
    public Servicio actualizarServicio(Servicio servicio) {

        if (servicio.getServicioId() == null || !serviciosRepository.existsById(servicio.getServicioId())) {
            throw new EntityNotFoundException("El servicio con ID " + servicio.getServicioId() + " no existe.");
        }

        // Si el servicio existe, proceder con la actualización
        return serviciosRepository.save(servicio);
    }


    @Override
    public Set<Servicio> obtenerServicio() {
        return new LinkedHashSet<>(serviciosRepository.findAll());
    }

    @Override
    public Servicio obtenerServicios(Long servicioId) {
        return serviciosRepository.findById(servicioId).get();
    }



    @Override
    public void eliminarServicio(Long servicioId) {
        if (serviciosRepository.existsById(servicioId)) {
            serviciosRepository.deleteById(servicioId);
        } else {
            throw new EntityNotFoundException("El servicio con ID " + servicioId + " no existe.");
        }
    }
}
