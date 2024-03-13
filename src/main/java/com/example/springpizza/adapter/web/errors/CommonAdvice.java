package com.example.springpizza.adapter.web.errors;

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

@RestControllerAdvice
@RequiredArgsConstructor
public class CommonAdvice extends ResponseEntityExceptionHandler {

    private final static String VALIDATION_ERROR = "validation_error";

    @ExceptionHandler
    public ErrorResponse handleNotFound(NotFoundException ex) {
        return new ErrorResponse(ex.getCode(), ex.getLocalizedMessage());
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


    // with another status
//    @ExceptionHandler
//    public ResponseEntity<ErrorResponse> handleNotFound(NotFoundException ex) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                .body(new ErrorResponse(ex.getCode(), ex.getLocalizedMessage()));
//    }

    // ResponseEntityExceptionHandler let you rewrite standard exceptions
//    @Override
//    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
//            HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//        return ResponseEntity.status(403).body("Something went wrong");
//    }
}
