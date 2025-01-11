package com.example.aos.sistema_aos_spring_boot.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "DetalleCatalogo")
public class DetalleCatalogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long DetalleCatalogoId;

    private Double costoCompraUSD;
    private Double TipoCambio;
    private Double utilidad;
    private Double costoUnitarioSoles;

    @ManyToOne(fetch = FetchType.EAGER)
    private Productos producto;

    @ManyToOne(fetch = FetchType.EAGER)
    private Catalogo catalogo;

    public Long getDetalleCatalogoId() {
        return DetalleCatalogoId;
    }

    public void setDetalleCatalogoId(Long detalleCatalogoId) {
        DetalleCatalogoId = detalleCatalogoId;
    }

    public Double getCostoCompraUSD() {
        return costoCompraUSD;
    }

    public void setCostoCompraUSD(Double costoCompraUSD) {
        this.costoCompraUSD = costoCompraUSD;
    }

    public Double getTipoCambio() {
        return TipoCambio;
    }

    public void setTipoCambio(Double tipoCambio) {
        TipoCambio = tipoCambio;
    }

    public Double getUtilidad() {
        return utilidad;
    }

    public void setUtilidad(Double utilidad) {
        this.utilidad = utilidad;
    }

    public Double getCostoUnitarioSoles() {
        return costoUnitarioSoles;
    }

    public void setCostoUnitarioSoles(Double costoUnitarioSoles) {
        this.costoUnitarioSoles = costoUnitarioSoles;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public Catalogo getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(Catalogo catalogo) {
        this.catalogo = catalogo;
    }

    public DetalleCatalogo() {
    }
}
