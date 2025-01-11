package com.example.aos.sistema_aos_spring_boot.Exceptions;

public class SkuExistenteException extends RuntimeException {

    public SkuExistenteException(String message) {
        super(message);
    }
}