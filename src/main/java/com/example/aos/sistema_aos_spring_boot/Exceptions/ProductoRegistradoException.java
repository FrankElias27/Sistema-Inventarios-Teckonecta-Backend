package com.example.aos.sistema_aos_spring_boot.Exceptions;

public class ProductoRegistradoException extends RuntimeException {

    // Constructor que recibe un mensaje
    public ProductoRegistradoException(String mensaje) {
        super(mensaje);
    }
}
