package com.example.aos.sistema_aos_spring_boot.Modelo;


import javax.persistence.*;

@Entity
@Table(name = "DetalleServicios")
public class DetalleServicios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detalleServiciosId;

    private Double costoUnitario;

    @ManyToOne( fetch = FetchType.EAGER)
    private Servicio servicio;

    @ManyToOne( fetch = FetchType.EAGER)
    private Cotizacion cotizacion;

    public Long getDetalleServiciosId() {
        return detalleServiciosId;
    }

    public void setDetalleServiciosId(Long detalleServiciosId) {
        this.detalleServiciosId = detalleServiciosId;
    }

    public Double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(Double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }

    public DetalleServicios() {
    }
}
