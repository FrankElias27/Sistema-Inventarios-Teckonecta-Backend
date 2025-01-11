package com.example.aos.sistema_aos_spring_boot.Exceptions;

public class ImagenProcesamientoException extends RuntimeException {
    public ImagenProcesamientoException(String message) {
        super(message);
    }

    public ImagenProcesamientoException(String message, Throwable cause) {
        super(message, cause);
    }
}
