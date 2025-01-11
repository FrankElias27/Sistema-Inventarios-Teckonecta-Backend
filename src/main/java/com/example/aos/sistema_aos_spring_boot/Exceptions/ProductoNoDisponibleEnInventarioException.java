package com.example.aos.sistema_aos_spring_boot.Exceptions;

public class ProductoNoDisponibleEnInventarioException extends RuntimeException {
    public ProductoNoDisponibleEnInventarioException(String message) {
        super(message);
    }
}

