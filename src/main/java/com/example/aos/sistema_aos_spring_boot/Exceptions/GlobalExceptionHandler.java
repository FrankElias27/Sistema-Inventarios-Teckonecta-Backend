package com.example.aos.sistema_aos_spring_boot.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception e, WebRequest request) {
        // Obtener información del controlador o endpoint que produjo la falla
        String controllerName = request.getDescription(false); // Descripción de la solicitud

        // Devolver una respuesta de error genérica
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Ha ocurrido un error inesperado. Por favor, inténtelo de nuevo más tarde.");
    }
}
