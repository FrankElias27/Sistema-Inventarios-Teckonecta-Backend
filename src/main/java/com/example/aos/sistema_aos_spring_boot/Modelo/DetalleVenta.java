package com.example.aos.sistema_aos_spring_boot.Modelo;

import javax.persistence.*;

@Entity
@Table(name = "DetalleVenta")
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detalleVentaId;
    private String cantidad;
    private String subtotal;

    @ManyToOne( fetch = FetchType.EAGER)
    private Productos producto;

    @ManyToOne( fetch = FetchType.EAGER)
    private Ventas venta;

    public Long getDetalleVentaId() {
        return detalleVentaId;
    }

    public void setDetalleVentaId(Long detalleVentaId) {
        this.detalleVentaId = detalleVentaId;
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

    public Ventas getVenta() {
        return venta;
    }

    public void setVenta(Ventas venta) {
        this.venta = venta;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public DetalleVenta() {
    }
}
