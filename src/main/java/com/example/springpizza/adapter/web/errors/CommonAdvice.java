package com.example.springpizza.adapter.web.errors;

import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
public class CommonAdvice extends ResponseEntityExceptionHandler {

    private final static String VALIDATION_ERROR = "validation_error";

    @ExceptionHandler
    public ErrorResponse handleNotFound(NotFoundException ex) {
        return new ErrorResponse(ex.getCode(), ex.getLocalizedMessage());
    }

    @ExceptionHandler
    public ErrorResponse handleMessageException(MessageException exception) {
        return new ErrorResponse(exception.getCode(), exception.getMessage());
    }

    @ExceptionHandler
    public List<ErrorResponse> handleConstraints(ConstraintViolationException ex) {
        return ex.getConstraintViolations()
                .stream().map(e -> new ErrorResponse(VALIDATION_ERROR, e.getPropertyPath().toString(), e.getMessage())).toList();
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var errors = new ArrayList<>(ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> new ErrorResponse(VALIDATION_ERROR, e.getField(), e.getDefaultMessage())).toList());
        errors.addAll(ex.getBindingResult().getGlobalErrors().stream()
                .map(e -> new ErrorResponse(VALIDATION_ERROR, e.getDefaultMessage())).toList());
        return ResponseEntity.badRequest()
                .body(errors);
    }
}
