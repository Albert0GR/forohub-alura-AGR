package com.agrsystems.forohub.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class TratadorDeErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404(){
        return ResponseEntity.notFound().build();

    }

    /*@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e){
        var errores = e.getAllErrors();
        return ResponseEntity.badRequest().body(errores);

    }*/
   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<Map<String, String>> tratarError400(MethodArgumentNotValidException e) {
       Map<String, String> errores = new HashMap<>();
       e.getBindingResult().getAllErrors().forEach((error) -> {
           String nombreCampo = ((FieldError) error).getField();
           String mensajeError = error.getDefaultMessage();
           errores.put(nombreCampo, mensajeError);
       });
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
   }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> manejarErroresGenerales(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor: " + e.getMessage());
    }

}
