package com.example.aos.sistema_aos_spring_boot.Modelo;


import javax.persistence.*;

@Entity
@Table(name = "DetalleCotizacion")
public class DetalleCotizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detalleCotizacionId;

    private Double cantidad;
    private Double costoUnitario;
    private Double subtotal;

    @ManyToOne( fetch = FetchType.EAGER)
    private Productos producto;

    @ManyToOne( fetch = FetchType.EAGER)
    private Cotizacion cotizacion;

    public Long getDetalleCotizacionId() {
        return detalleCotizacionId;
    }

    public void setDetalleCotizacionId(Long detalleCotizacionId) {
        this.detalleCotizacionId = detalleCotizacionId;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }

    public Double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(Double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public DetalleCotizacion() {
    }
}
