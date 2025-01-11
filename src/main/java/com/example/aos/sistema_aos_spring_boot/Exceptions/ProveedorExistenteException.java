package com.example.aos.sistema_aos_spring_boot.Exceptions;

public class ProveedorExistenteException extends RuntimeException {
    public ProveedorExistenteException(String message) {
        super(message);
    }
}
