package com.gce.ba.homework.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String defaultMessage = null;

        for (FieldError err : ex.getBindingResult().getFieldErrors()) {
            defaultMessage = err.getDefaultMessage();
        }
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(LocalDateTime.now(), "Bad argument", defaultMessage);
        return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        CustomErrorDetails customErrorDetails = new CustomErrorDetails(LocalDateTime.now(), "Not supported HTTP request method", ex.getMessage() + ". Supported methods " + ex.getSupportedHttpMethods());
        return new ResponseEntity<>(customErrorDetails, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(PaymentNotFoundException.class)
    public final ResponseEntity<Object> handlePaymentNotFoundException(PaymentNotFoundException ex, WebRequest request) {
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(customErrorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CancelationNotAvailableException.class)
    public final ResponseEntity<Object> handleCancelationNotAvailableException(CancelationNotAvailableException ex, WebRequest request) {
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(customErrorDetails, HttpStatus.NOT_ACCEPTABLE);
    }

}
