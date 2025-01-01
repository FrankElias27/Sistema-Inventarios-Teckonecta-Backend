package com.example.aos.sistema_aos_spring_boot.Servicios;

import com.example.aos.sistema_aos_spring_boot.Modelo.Cliente;
import com.example.aos.sistema_aos_spring_boot.Modelo.Servicio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface ServiciosService {

    Servicio agregarServicio(Servicio servicio);

    Servicio actualizarServicio(Servicio servicio);

    Set<Servicio> obtenerServicio();

    Servicio obtenerServicios(Long servicioId);

    Page<Servicio> findAll(Pageable pageable);

    void eliminarServicio(Long servicioId);
}
