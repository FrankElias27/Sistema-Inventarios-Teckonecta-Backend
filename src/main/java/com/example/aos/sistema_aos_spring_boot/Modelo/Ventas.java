package com.example.aos.sistema_aos_spring_boot.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Venta")
public class Ventas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long VentaId;

    private String FechaVenta;
    private String NumTotaldeDetalleVenta;
    private String TotalaPagar;
    private String Estado;
    private boolean activo = false;

    @OneToMany(mappedBy = "venta",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<DetalleVenta> detalleVenta  = new HashSet<>();

    @ManyToOne( fetch = FetchType.EAGER)
    private Cliente cliente;

    public Long getVentaId() {
        return VentaId;
    }

    public void setVentaId(Long ventaId) {
        VentaId = ventaId;
    }

    public String getFechaVenta() {
        return FechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        FechaVenta = fechaVenta;
    }

    public String getNumTotaldeDetalleVenta() {
        return NumTotaldeDetalleVenta;
    }

    public void setNumTotaldeDetalleVenta(String numTotaldeDetalleVenta) {
        NumTotaldeDetalleVenta = numTotaldeDetalleVenta;
    }

    public String getTotalaPagar() {
        return TotalaPagar;
    }

    public void setTotalaPagar(String totalaPagar) {
        TotalaPagar = totalaPagar;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
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

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public Ventas() {
    }
}
