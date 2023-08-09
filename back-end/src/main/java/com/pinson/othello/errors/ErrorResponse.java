package com.pinson.othello.errors;

import java.time.LocalDateTime;

public record ErrorResponse(
    Integer status,
    String message,
    LocalDateTime timestamp
) {
    //
}
