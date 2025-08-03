package com.simple_inventory_tracker.project.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.simple_inventory_tracker.project.dto.ErrorMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logerror = LoggerFactory.getLogger(GlobalExceptionHandler.class);
     @ExceptionHandler({ProductNotFoundException.class, StockNotFoundException.class, StockQuantityInsufficientException.class})
    public ResponseEntity<ErrorMessage> handleResourceRelatedException(RuntimeException e){
        ErrorMessage error = new ErrorMessage(e.getMessage(), LocalDateTime.now());
        StackTraceElement trace = e.getStackTrace()[0];
        String traceMethod = trace.getMethodName();
                logerror.error(traceMethod + " : " + e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGeneralException(Exception e){
        ErrorMessage error = new ErrorMessage(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    
}
