package com.example.aos.sistema_aos_spring_boot.Modelo;

import com.example.aos.sistema_aos_spring_boot.Enums.Estado;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Venta")
public class Ventas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ventaId;

    private LocalDateTime FechaVenta;
    private Double NumTotalProducto;
    private Double TotalaPagar;
    private Estado Estado;

    @OneToMany(mappedBy = "venta",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<DetalleVenta> detalleVenta  = new HashSet<>();

    @ManyToOne( fetch = FetchType.EAGER)
    private Cliente cliente;

    public Long getVentaId() {
        return ventaId;
    }

    public void setVentaId(Long ventaId) {
        this.ventaId = ventaId;
    }

    public LocalDateTime getFechaVenta() {
        return FechaVenta;
    }

    public void setFechaVenta(LocalDateTime fechaVenta) {
        FechaVenta = fechaVenta;
    }

    public void setTotalaPagar(Double totalaPagar) {
        TotalaPagar = totalaPagar;
    }

    public Double getNumTotalProducto() {
        return NumTotalProducto;
    }

    public void setNumTotalProducto(Double numTotalProducto) {
        NumTotalProducto = numTotalProducto;
    }

    public Double getTotalaPagar() {
        return TotalaPagar;
    }

    public Estado getEstado() {
        return Estado;
    }

    public void setEstado(Estado estado) {
        Estado = estado;
    }

    public Set<DetalleVenta> getDetalleVenta() {
        return detalleVenta;
    }

    public void setDetalleVenta(Set<DetalleVenta> detalleVenta) {
        this.detalleVenta = detalleVenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Ventas() {
    }
}
