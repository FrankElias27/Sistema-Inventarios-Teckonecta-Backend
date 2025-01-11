package com.example.aos.sistema_aos_spring_boot.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "DetalleCompra")
public class DetalleCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detalleCompraId;

    private Double cantidad;
    private Double costoCompraUSD;
    private Double TipoCambio;
    private Double costoUnitarioSoles;
    private Double subtotal;

    @ManyToOne( fetch = FetchType.EAGER)
    private Productos producto;

    @ManyToOne( fetch = FetchType.EAGER)
    private Compras compra;

    @OneToMany(mappedBy = "detalleCompra",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Kardex> kardexcompra  = new HashSet<>();

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

    public Double getCostoCompraUSD() {
        return costoCompraUSD;
    }

    public void setCostoCompraUSD(Double costoCompraUSD) {
        this.costoCompraUSD = costoCompraUSD;
    }

    public Double getTipoCambio() {
        return TipoCambio;
    }

    public void setTipoCambio(Double tipoCambio) {
        TipoCambio = tipoCambio;
    }

    public Double getCostoUnitarioSoles() {
        return costoUnitarioSoles;
    }

    public void setCostoUnitarioSoles(Double costoUnitarioSoles) {
        this.costoUnitarioSoles = costoUnitarioSoles;
    }

    public Set<Kardex> getKardexcompra() {
        return kardexcompra;
    }

    public void setKardexcompra(Set<Kardex> kardexcompra) {
        this.kardexcompra = kardexcompra;
    }

    public DetalleCompra() {
    }
}
