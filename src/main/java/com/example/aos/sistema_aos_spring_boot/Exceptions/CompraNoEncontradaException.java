package com.example.aos.sistema_aos_spring_boot.Exceptions;

public class CompraNoEncontradaException extends RuntimeException {
    public CompraNoEncontradaException(String message) {
        super(message);
    }
}
