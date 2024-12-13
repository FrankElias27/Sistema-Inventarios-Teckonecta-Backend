package com.example.aos.sistema_aos_spring_boot.Servicios;

import com.example.aos.sistema_aos_spring_boot.Modelo.Servicio;

import java.util.Set;

public interface ServiciosService {

    Servicio agregarServicio(Servicio servicio);

    Servicio actualizarServicio(Servicio servicio);

    Set<Servicio> obtenerServicio();

    Servicio obtenerServicios(Long servicioId);

    void eliminarServicio(Long servicioId);
}
