package com.example.aos.sistema_aos_spring_boot.Exceptions;

public class ProductoExistenteException extends RuntimeException {
    public ProductoExistenteException(String message) {
        super(message);
    }
}
