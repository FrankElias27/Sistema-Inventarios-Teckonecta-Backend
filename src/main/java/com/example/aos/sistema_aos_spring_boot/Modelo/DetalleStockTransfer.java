package com.example.aos.sistema_aos_spring_boot.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "Stock_Transfer_Detalle")
public class DetalleStockTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transferDetailId;

    private Double cantidad;

    @ManyToOne(fetch = FetchType.EAGER)
    private StockTransfer stockTransfer;

    @ManyToOne(fetch = FetchType.EAGER)
    private Productos producto;

    public Long getTransferDetailId() {
        return transferDetailId;
    }

    public void setTransferDetailId(Long transferDetailId) {
        this.transferDetailId = transferDetailId;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public StockTransfer getStockTransfer() {
        return stockTransfer;
    }

    public void setStockTransfer(StockTransfer stockTransfer) {
        this.stockTransfer = stockTransfer;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public DetalleStockTransfer() {
    }
}







