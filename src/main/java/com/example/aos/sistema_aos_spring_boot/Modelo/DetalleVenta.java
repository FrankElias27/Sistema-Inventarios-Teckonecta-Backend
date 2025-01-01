package com.example.aos.sistema_aos_spring_boot.Modelo;

import javax.persistence.*;

@Entity
@Table(name = "DetalleVenta")
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detalleVentaId;

    private Double cantidad;
    private Double costoUnitario;
    private Double subtotal;

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

    public Double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(Double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public DetalleVenta() {
    }
}
