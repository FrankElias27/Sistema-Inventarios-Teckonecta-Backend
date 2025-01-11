package com.example.aos.sistema_aos_spring_boot.Modelo;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Proveedor")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long proveedorId;

    private String RazonSocial;
    private String Direccion;
    private String RUC;
    private String Correo;
    private String Telefono;

    @Column(length = 2000)
    private String InfoProductos;

    @OneToMany(mappedBy = "proveedor",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Compras> compraProveedor  = new HashSet<>();

    @OneToMany(mappedBy = "proveedor",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<AsesorVenta> asesorVentas  = new HashSet<>();

    public Long getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Long proveedorId) {
        this.proveedorId = proveedorId;
    }

    public String getRazonSocial() {
        return RazonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        RazonSocial = razonSocial;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getRUC() {
        return RUC;
    }

    public void setRUC(String RUC) {
        this.RUC = RUC;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public Set<Compras> getCompraProveedor() {
        return compraProveedor;
    }

    public void setCompraProveedor(Set<Compras> compraProveedor) {
        this.compraProveedor = compraProveedor;
    }

    public Set<AsesorVenta> getAsesorVentas() {
        return asesorVentas;
    }

    public void setAsesorVentas(Set<AsesorVenta> asesorVentas) {
        this.asesorVentas = asesorVentas;
    }

    public String getInfoProductos() {
        return InfoProductos;
    }

    public void setInfoProductos(String infoProductos) {
        InfoProductos = infoProductos;
    }

    public Proveedor() {
    }
}
