package com.example.aos.sistema_aos_spring_boot.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "Stock_Transfers")
public class StockTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transferId;

    private Double cantidad;

    private LocalDateTime FechaTransferencia = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "from_inventory_id", nullable = false)
    private Inventario fromInventory;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "to_inventory_id", nullable = false)
    private Inventario toInventory;

    @ManyToOne(fetch = FetchType.EAGER)
    private Productos producto;


    @OneToOne(mappedBy = "stockTransfer",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private GuiaRemision guiaRemisions;

    @OneToMany(mappedBy = "stockTransfer",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<DetalleStockTransfer> detalleStockTransfer  = new HashSet<>();

    public Long getTransferId() {
        return transferId;
    }

    public void setTransferId(Long transferId) {
        this.transferId = transferId;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDateTime getFechaTransferencia() {
        return FechaTransferencia;
    }

    public void setFechaTransferencia(LocalDateTime fechaTransferencia) {
        FechaTransferencia = fechaTransferencia;
    }

    public Inventario getFromInventory() {
        return fromInventory;
    }

    public void setFromInventory(Inventario fromInventory) {
        this.fromInventory = fromInventory;
    }

    public Inventario getToInventory() {
        return toInventory;
    }

    public void setToInventory(Inventario toInventory) {
        this.toInventory = toInventory;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public GuiaRemision getGuiaRemisions() {
        return guiaRemisions;
    }

    public void setGuiaRemisions(GuiaRemision guiaRemisions) {
        this.guiaRemisions = guiaRemisions;
    }

    public Set<DetalleStockTransfer> getDetalleStockTransfer() {
        return detalleStockTransfer;
    }

    public void setDetalleStockTransfer(Set<DetalleStockTransfer> detalleStockTransfer) {
        this.detalleStockTransfer = detalleStockTransfer;
    }

    public StockTransfer() {
    }
}
