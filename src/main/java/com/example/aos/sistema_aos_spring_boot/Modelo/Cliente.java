package com.example.aos.sistema_aos_spring_boot.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clienteId;

    private String Nombre;
    private String ApellidoPaterno;
    private String ApellidoMaterno;
    private LocalDate FechaNacimiento;
    private String Direccion;
    private String DNI;
    private String Correo;
    private String Telefono;

    @OneToMany(mappedBy = "cliente",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Ventas> ventaCliente  = new HashSet<>();

    @OneToMany(mappedBy = "cliente",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Cotizacion> cotizacionCliente  = new HashSet<>();

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidoPaterno() {
        return ApellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        ApellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return ApellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        ApellidoMaterno = apellidoMaterno;
    }

    public LocalDate getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        FechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
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

    public Set<Ventas> getVentaCliente() {
        return ventaCliente;
    }

    public void setVentaCliente(Set<Ventas> ventaCliente) {
        this.ventaCliente = ventaCliente;
    }

    public Set<Cotizacion> getCotizacionCliente() {
        return cotizacionCliente;
    }

    public void setCotizacionCliente(Set<Cotizacion> cotizacionCliente) {
        this.cotizacionCliente = cotizacionCliente;
    }

    public Cliente() {
    }
}