package com.example.aos.sistema_aos_spring_boot.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Guia_Remision")
public class GuiaRemision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long guideId;

    private String NumeroGuia;

    private LocalDateTime FechaCreacion = LocalDateTime.now();

    @OneToOne(fetch = FetchType.EAGER)
    private StockTransfer stockTransfer;

    public Long getGuideId() {
        return guideId;
    }

    public void setGuideId(Long guideId) {
        this.guideId = guideId;
    }

    public String getNumeroGuia() {
        return NumeroGuia;
    }

    public void setNumeroGuia(String numeroGuia) {
        NumeroGuia = numeroGuia;
    }

    public LocalDateTime getFechaCreacion() {
        return FechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        FechaCreacion = fechaCreacion;
    }

    public StockTransfer getStockTransfer() {
        return stockTransfer;
    }

    public void setStockTransfer(StockTransfer stockTransfer) {
        this.stockTransfer = stockTransfer;
    }

    public GuiaRemision() {
    }
}







