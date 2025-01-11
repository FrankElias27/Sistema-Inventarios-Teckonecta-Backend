package com.example.aos.sistema_aos_spring_boot.Exceptions;

public class InvalidImageFormatException extends RuntimeException {

    public InvalidImageFormatException(String message) {
        super(message);
    }
}
