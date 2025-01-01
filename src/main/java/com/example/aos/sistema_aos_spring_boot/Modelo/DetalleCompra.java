package com.example.aos.sistema_aos_spring_boot.Modelo;

import javax.persistence.*;

@Entity
@Table(name = "DetalleCompra")
public class DetalleCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detalleCompraId;

    private Double cantidad;
    private Double costoUnitario;
    private Double subtotal;

    @ManyToOne( fetch = FetchType.EAGER)
    private Productos producto;

    @ManyToOne( fetch = FetchType.EAGER)
    private Compras compra;

    public Long getDetalleCompraId() {
        return detalleCompraId;
    }

    public void setDetalleCompraId(Long detalleCompraId) {
        this.detalleCompraId = detalleCompraId;
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

    public Compras getCompra() {
        return compra;
    }

    public void setCompra(Compras compra) {
        this.compra = compra;
    }

    public Double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(Double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public DetalleCompra() {
    }
}
