package com.example.aos.sistema_aos_spring_boot.Servicios.impl;

import com.example.aos.sistema_aos_spring_boot.Modelo.Categoria;
import com.example.aos.sistema_aos_spring_boot.Modelo.Servicio;
import com.example.aos.sistema_aos_spring_boot.Repositorios.CategoriaRepository;
import com.example.aos.sistema_aos_spring_boot.Repositorios.ServiciosRepository;
import com.example.aos.sistema_aos_spring_boot.Servicios.ServiciosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Servicio actualizarServicio(Servicio servicio) {
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
        Servicio servicio = new Servicio();
        servicio.setServicioId(servicioId);
        serviciosRepository.delete(servicio);
    }
}
