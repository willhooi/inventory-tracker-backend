package com.simple_inventory_tracker.project.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.simple_inventory_tracker.project.dto.ErrorMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            ProductNotFoundException.class,
            StockNotFoundException.class,
            StockQuantityInsufficientException.class
    })

    public ResponseEntity<ErrorMessage> handleResourceRelatedException(RuntimeException e) {
        log.warn("Resource exception: {}", e.getMessage());
        ErrorMessage error = new ErrorMessage(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)

    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        log.warn("Validation error occurred: {}", ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err -> {
            log.debug("Field '{}' error: {}", err.getField(), err.getDefaultMessage());
            errors.put(err.getField(), err.getDefaultMessage());
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGeneralException(Exception e) {
        log.error("Unexpected error: {}", e.getMessage(), e);
        ErrorMessage error = new ErrorMessage(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
