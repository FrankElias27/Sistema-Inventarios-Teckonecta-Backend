package com.example.aos.sistema_aos_spring_boot.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Producto")
public class Productos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ProductoId;

    private String nombre;


    private String URLImagen;
    private String codigo;
    private Double stock;
    private Double precioCompra;
    private Double precioVenta;
    private Double precioCotizacion;
    private boolean activo = false;

    @Column(length = 2000)
    private String descripcion;

    @ManyToOne(fetch = FetchType.EAGER)
    private Categoria categoria;

    @OneToMany(mappedBy = "producto",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<DetalleVenta> detalleVenta2  = new HashSet<>();

    @OneToMany(mappedBy = "producto",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<DetalleCompra> detalleCompra2 = new HashSet<>();

    @OneToMany(mappedBy = "producto",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<DetalleCotizacion> detalleCotizacion2 = new HashSet<>();

    public Long getProductoId() {
        return ProductoId;
    }

    public void setProductoId(Long productoId) {
        ProductoId = productoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getURLImagen() {
        return URLImagen;
    }

    public void setURLImagen(String URLImagen) {
        this.URLImagen = URLImagen;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    public Double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Double getPrecioCotizacion() {
        return precioCotizacion;
    }

    public void setPrecioCotizacion(Double precioCotizacion) {
        this.precioCotizacion = precioCotizacion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Set<DetalleVenta> getDetalleVenta2() {
        return detalleVenta2;
    }

    public void setDetalleVenta2(Set<DetalleVenta> detalleVenta2) {
        this.detalleVenta2 = detalleVenta2;
    }

    public Set<DetalleCompra> getDetalleCompra2() {
        return detalleCompra2;
    }

    public void setDetalleCompra2(Set<DetalleCompra> detalleCompra2) {
        this.detalleCompra2 = detalleCompra2;
    }

    public Set<DetalleCotizacion> getDetalleCotizacion2() {
        return detalleCotizacion2;
    }

    public void setDetalleCotizacion2(Set<DetalleCotizacion> detalleCotizacion2) {
        this.detalleCotizacion2 = detalleCotizacion2;
    }


    public Productos() {
    }
}

