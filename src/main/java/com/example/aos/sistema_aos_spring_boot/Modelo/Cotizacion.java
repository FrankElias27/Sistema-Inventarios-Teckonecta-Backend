package com.example.aos.sistema_aos_spring_boot.Modelo;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Cotizacion")
public class Cotizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CotizacionId;

    private String FechaCotizacion;
    private String AsuntoCotizacion;
    private String NumTotaldeDetalle;
    private String SubTotalCotizacion;
    private String SubTotalServicios;
    private String EstadoCotizacion;
    private String Total;
    private boolean activo = false;

    @OneToMany(mappedBy = "cotizacion",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<DetalleCotizacion> detalleCotizacion  = new HashSet<>();

    @OneToMany(mappedBy = "cotizacion",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<DetalleServicios> detalleServicios  = new HashSet<>();

    @ManyToOne( fetch = FetchType.EAGER)
    private Cliente cliente;

    public Long getCotizacionId() {
        return CotizacionId;
    }

    public void setCotizacionId(Long cotizacionId) {
        CotizacionId = cotizacionId;
    }

    public String getFechaCotizacion() {
        return FechaCotizacion;
    }

    public void setFechaCotizacion(String fechaCotizacion) {
        FechaCotizacion = fechaCotizacion;
    }

    public String getAsuntoCotizacion() {
        return AsuntoCotizacion;
    }

    public void setAsuntoCotizacion(String asuntoCotizacion) {
        AsuntoCotizacion = asuntoCotizacion;
    }

    public String getNumTotaldeDetalle() {
        return NumTotaldeDetalle;
    }

    public void setNumTotaldeDetalle(String numTotaldeDetalle) {
        NumTotaldeDetalle = numTotaldeDetalle;
    }

    public String getSubTotalCotizacion() {
        return SubTotalCotizacion;
    }

    public void setSubTotalCotizacion(String subTotalCotizacion) {
        SubTotalCotizacion = subTotalCotizacion;
    }

    public String getSubTotalServicios() {
        return SubTotalServicios;
    }

    public void setSubTotalServicios(String subTotalServicios) {
        SubTotalServicios = subTotalServicios;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
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

    public String getEstadoCotizacion() {
        return EstadoCotizacion;
    }

    public void setEstadoCotizacion(String estadoCotizacion) {
        EstadoCotizacion = estadoCotizacion;
    }

    public Cotizacion() {
    }
}
