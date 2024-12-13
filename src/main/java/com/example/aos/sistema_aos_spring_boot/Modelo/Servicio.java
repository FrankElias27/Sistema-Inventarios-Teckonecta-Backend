package com.example.aos.sistema_aos_spring_boot.Modelo;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Servicio")
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ServicioId;
    private String NombreServicio;

    @OneToMany(mappedBy = "servicio",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<DetalleServicios> detalleServicios  = new HashSet<>();

    public Long getServicioId() {
        return ServicioId;
    }

    public void setServicioId(Long servicioId) {
        ServicioId = servicioId;
    }

    public String getNombreServicio() {
        return NombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        NombreServicio = nombreServicio;
    }


    public Set<DetalleServicios> getDetalleServicios() {
        return detalleServicios;
    }

    public void setDetalleServicios(Set<DetalleServicios> detalleServicios) {
        this.detalleServicios = detalleServicios;
    }

    public Servicio() {
    }
}
