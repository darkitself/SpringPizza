package com.example.springpizza.adapter.web.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CommonAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ErrorResponse handleNotFound(NotFoundException ex) {
        return new ErrorResponse(ex.getCode(), ex.getLocalizedMessage());
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
