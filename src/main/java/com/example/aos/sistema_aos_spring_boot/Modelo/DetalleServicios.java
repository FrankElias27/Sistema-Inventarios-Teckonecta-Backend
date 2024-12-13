package com.example.aos.sistema_aos_spring_boot.Modelo;


import javax.persistence.*;

@Entity
@Table(name = "DetalleServicios")
public class DetalleServicios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long DetalleServiciosId;
    private String subtotal;

    @ManyToOne( fetch = FetchType.EAGER)
    private Servicio servicio;

    @ManyToOne( fetch = FetchType.EAGER)
    private Cotizacion cotizacion;

    public Long getDetalleServiciosId() {
        return DetalleServiciosId;
    }

    public void setDetalleServiciosId(Long detalleServiciosId) {
        DetalleServiciosId = detalleServiciosId;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
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
