package com.example.aos.sistema_aos_spring_boot.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "Inventario")
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventarioId;

    private String nombre;

    private String ubicacion;

    @OneToMany(mappedBy = "inventario",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Kardex> kardexs  = new HashSet<>();

    @OneToMany(mappedBy = "inventario",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<InventarioStock> inventarioStocks  = new HashSet<>();

    public Long getInventarioId() {
        return inventarioId;
    }

    public void setInventarioId(Long inventarioId) {
        this.inventarioId = inventarioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Set<InventarioStock> getInventarioStocks() {
        return inventarioStocks;
    }

    public void setInventarioStocks(Set<InventarioStock> inventarioStocks) {
        this.inventarioStocks = inventarioStocks;
    }

    public Set<Kardex> getKardexs() {
        return kardexs;
    }

    public void setKardexs(Set<Kardex> kardexs) {
        this.kardexs = kardexs;
    }

    public Inventario() {
    }

}