package com.example.aos.sistema_aos_spring_boot.Modelo;

import javax.persistence.*;

@Entity
@Table(name = "DetalleCompra")
public class DetalleCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detalleCompraId;
    private String cantidad;
    private String subtotal;

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

    public Compras getCompra() {
        return compra;
    }

    public void setCompra(Compras compra) {
        this.compra = compra;
    }

    public DetalleCompra() {
    }
}
