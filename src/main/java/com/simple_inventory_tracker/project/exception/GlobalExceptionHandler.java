package com.simple_inventory_tracker.project.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.simple_inventory_tracker.project.dto.ErrorMessage;

@ControllerAdvice
public class GlobalExceptionHandler {
     @ExceptionHandler({ProductNotFoundException.class, StockNotFoundException.class, StockQuantityInsufficientException.class})
    public ResponseEntity<ErrorMessage> handleResourceRelatedException(RuntimeException e){
        ErrorMessage error = new ErrorMessage(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGeneralException(Exception e){
        ErrorMessage error = new ErrorMessage(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    
}
