package com.example.aos.sistema_aos_spring_boot.Modelo;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Asesor_Venta")
public class AsesorVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long asesorId;

    private String nombre;

    private String telefono;

    private String infoextra;

    @ManyToOne(fetch = FetchType.EAGER)
    private Proveedor proveedor;

    public Long getAsesorId() {
        return asesorId;
    }

    public void setAsesorId(Long asesorId) {
        this.asesorId = asesorId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getInfoextra() {
        return infoextra;
    }

    public void setInfoextra(String infoextra) {
        this.infoextra = infoextra;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public AsesorVenta() {
    }
}
