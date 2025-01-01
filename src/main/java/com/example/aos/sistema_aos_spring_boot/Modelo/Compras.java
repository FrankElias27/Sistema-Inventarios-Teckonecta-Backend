package com.example.aos.sistema_aos_spring_boot.Modelo;

import com.example.aos.sistema_aos_spring_boot.Enums.Estado;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Compra")
public class Compras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long compraId;

    private LocalDateTime FechaCompra;
    private Double NumTotalProductos;
    private Double TotalaPagar;
    private Estado Estado;

    @OneToMany(mappedBy = "compra",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<DetalleCompra> detalleCompra  = new HashSet<>();

    @ManyToOne( fetch = FetchType.EAGER)
    private Proveedor proveedor;

    public Long getCompraId() {
        return compraId;
    }

    public void setCompraId(Long compraId) {
        this.compraId = compraId;
    }

    public LocalDateTime getFechaCompra() {
        return FechaCompra;
    }

    public void setFechaCompra(LocalDateTime fechaCompra) {
        FechaCompra = fechaCompra;
    }

    public Double getNumTotalProductos() {
        return NumTotalProductos;
    }

    public void setNumTotalProductos(Double numTotalProductos) {
        NumTotalProductos = numTotalProductos;
    }

    public Double getTotalaPagar() {
        return TotalaPagar;
    }

    public void setTotalaPagar(Double totalaPagar) {
        TotalaPagar = totalaPagar;
    }

    public Estado getEstado() {
        return Estado;
    }

    public void setEstado(Estado estado) {
        Estado = estado;
    }

    public Set<DetalleCompra> getDetalleCompra() {
        return detalleCompra;
    }

    public void setDetalleCompra(Set<DetalleCompra> detalleCompra) {
        this.detalleCompra = detalleCompra;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Compras() {
    }
}
