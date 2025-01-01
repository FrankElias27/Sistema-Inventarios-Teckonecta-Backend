package com.example.aos.sistema_aos_spring_boot.Modelo;


import com.example.aos.sistema_aos_spring_boot.Enums.Estado;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Cotizacion")
public class Cotizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cotizacionId;

    private LocalDateTime FechaCotizacion;
    private String AsuntoCotizacion;
    private Double NumTotalProducto;
    private Double SubTotalCotizacion;
    private Double SubTotalServicios;
    private Estado EstadoCotizacion;
    private Double Total;

    @OneToMany(mappedBy = "cotizacion",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<DetalleCotizacion> detalleCotizacion  = new HashSet<>();

    @OneToMany(mappedBy = "cotizacion",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<DetalleServicios> detalleServicios  = new HashSet<>();

    @ManyToOne( fetch = FetchType.EAGER)
    private Cliente cliente;

    public Long getCotizacionId() {
        return cotizacionId;
    }

    public void setCotizacionId(Long cotizacionId) {
        this.cotizacionId = cotizacionId;
    }

    public LocalDateTime getFechaCotizacion() {
        return FechaCotizacion;
    }

    public void setFechaCotizacion(LocalDateTime fechaCotizacion) {
        FechaCotizacion = fechaCotizacion;
    }

    public String getAsuntoCotizacion() {
        return AsuntoCotizacion;
    }

    public void setAsuntoCotizacion(String asuntoCotizacion) {
        AsuntoCotizacion = asuntoCotizacion;
    }

    public Double getNumTotalProducto() {
        return NumTotalProducto;
    }

    public void setNumTotalProducto(Double numTotalProducto) {
        NumTotalProducto = numTotalProducto;
    }

    public Double getSubTotalCotizacion() {
        return SubTotalCotizacion;
    }

    public void setSubTotalCotizacion(Double subTotalCotizacion) {
        SubTotalCotizacion = subTotalCotizacion;
    }

    public Double getSubTotalServicios() {
        return SubTotalServicios;
    }

    public void setSubTotalServicios(Double subTotalServicios) {
        SubTotalServicios = subTotalServicios;
    }

    public Estado getEstadoCotizacion() {
        return EstadoCotizacion;
    }

    public void setEstadoCotizacion(Estado estadoCotizacion) {
        EstadoCotizacion = estadoCotizacion;
    }

    public Double getTotal() {
        return Total;
    }

    public void setTotal(Double total) {
        Total = total;
    }

    public Set<DetalleCotizacion> getDetalleCotizacion() {
        return detalleCotizacion;
    }

    public void setDetalleCotizacion(Set<DetalleCotizacion> detalleCotizacion) {
        this.detalleCotizacion = detalleCotizacion;
    }

    public Set<DetalleServicios> getDetalleServicios() {
        return detalleServicios;
    }

    public void setDetalleServicios(Set<DetalleServicios> detalleServicios) {
        this.detalleServicios = detalleServicios;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cotizacion() {
    }
}
