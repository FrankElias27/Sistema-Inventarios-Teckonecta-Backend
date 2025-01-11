package com.example.aos.sistema_aos_spring_boot.Exceptions;

public class ImageSizeExceededException extends RuntimeException {

    public ImageSizeExceededException(String message) {
        super(message);
    }
}
