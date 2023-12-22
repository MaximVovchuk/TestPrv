package com.example.business.util;

import com.example.daoService.exceptions.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionsHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAlreadyExistException(Exception ex) {
        log.info("Exception: " + ex.getMessage());
        return ResponseEntity.status(500).body(ex.getMessage());
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleAlreadyExistException(CustomException ex) {
        log.info("CustomException: " + ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }
}
