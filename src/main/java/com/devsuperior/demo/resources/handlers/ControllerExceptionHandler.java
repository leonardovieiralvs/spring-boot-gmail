package com.devsuperior.demo.resources.handlers;

import com.devsuperior.demo.dto.CustomErrorDTO;
import com.devsuperior.demo.services.exceptions.EmailException;
import com.devsuperior.demo.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(EmailException.class)
    public ResponseEntity<CustomErrorDTO> email(EmailException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomErrorDTO err = CustomErrorDTO.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error(e.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomErrorDTO> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomErrorDTO err = CustomErrorDTO.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error(e.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(status).body(err);
    }
}
