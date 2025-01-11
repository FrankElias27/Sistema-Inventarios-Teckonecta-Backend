package com.example.aos.sistema_aos_spring_boot.Exceptions;

public class InventarioNoEncontradoException extends RuntimeException {
    public InventarioNoEncontradoException(String message) {
        super(message);
    }
}
