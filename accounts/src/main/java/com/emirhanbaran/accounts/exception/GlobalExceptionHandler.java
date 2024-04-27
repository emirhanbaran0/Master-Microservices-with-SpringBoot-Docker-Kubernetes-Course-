package com.emirhanbaran.accounts.exception;

import com.emirhanbaran.accounts.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerAlreadyExistException.class)
    public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExistsException(CustomerAlreadyExistException ex, WebRequest request) {
        ErrorResponseDto errorResponseDto=new ErrorResponseDto(
                request.getDescription(false),
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                Instant.now()
        );
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResurceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorResponseDto errorResponseDto=new ErrorResponseDto(
                request.getDescription(false),
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                Instant.now()
        );
        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }
}
