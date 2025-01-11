package com.example.aos.sistema_aos_spring_boot.Exceptions;

public class VentaNoEncontradaException extends RuntimeException {
    public VentaNoEncontradaException(String message) {
        super(message);
    }
}
