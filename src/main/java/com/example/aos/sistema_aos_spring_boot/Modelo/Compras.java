package com.example.aos.sistema_aos_spring_boot.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Compra")
public class Compras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long compraId;

    private String FechaCompra;
    private String NumTotaldeProductos;
    private String TotalaPagar;
    private String Estado;

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

    public String getFechaCompra() {
        return FechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        FechaCompra = fechaCompra;
    }

    public String getNumTotaldeProductos() {
        return NumTotaldeProductos;
    }

    public void setNumTotaldeProductos(String numTotaldeProductos) {
        NumTotaldeProductos = numTotaldeProductos;
    }

    public String getTotalaPagar() {
        return TotalaPagar;
    }

    public void setTotalaPagar(String totalaPagar) {
        TotalaPagar = totalaPagar;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
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
