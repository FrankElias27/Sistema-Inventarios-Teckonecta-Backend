package com.example.aos.sistema_aos_spring_boot.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "catalogos")
public class Catalogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long catalogoId;

    private String nombre;

    @OneToMany(mappedBy = "catalogo",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<DetalleCatalogo> detalleCatalogo  = new HashSet<>();

    public Long getCatalogoId() {
        return catalogoId;
    }

    public void setCatalogoId(Long catalogoId) {
        this.catalogoId = catalogoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<DetalleCatalogo> getDetalleCatalogo() {
        return detalleCatalogo;
    }

    public void setDetalleCatalogo(Set<DetalleCatalogo> detalleCatalogo) {
        this.detalleCatalogo = detalleCatalogo;
    }

    public Catalogo() {
    }
}
