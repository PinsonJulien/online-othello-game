package com.pinson.othello.errors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ErrorResponseFactory {

    @Autowired
    public ErrorResponseFactory() {
        //
    }

    public ErrorResponse create(
        final HttpStatusCode status,
        final String message
    ) {
        return new ErrorResponse(
            status.value(),
            message,
            LocalDateTime.now()
        );
    }

    public ResponseEntity<Object> createResponse(
        final HttpStatusCode status,
        final String message
    ) {
        return ResponseEntity.status(status).body(
            this.create(status, message)
        );
    }

}
