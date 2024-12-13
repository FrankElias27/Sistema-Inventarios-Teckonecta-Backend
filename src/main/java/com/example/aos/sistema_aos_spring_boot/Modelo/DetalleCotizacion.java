package com.example.aos.sistema_aos_spring_boot.Modelo;


import javax.persistence.*;

@Entity
@Table(name = "DetalleCotizacion")
public class DetalleCotizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long DetalleCotizacionId;
    private String cantidad;
    private String subtotal;

    @ManyToOne( fetch = FetchType.EAGER)
    private Productos producto;

    @ManyToOne( fetch = FetchType.EAGER)
    private Cotizacion cotizacion;

    public Long getDetalleCotizacionId() {
        return DetalleCotizacionId;
    }

    public void setDetalleCotizacionId(Long detalleCotizacionId) {
        DetalleCotizacionId = detalleCotizacionId;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
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

    public DetalleCotizacion() {
    }
}
