package com.pinson.othello.commons.exceptions;

public class NonPositiveValueException extends Exception {

    public NonPositiveValueException() {
        super("The value must be positive.");
    }

    public NonPositiveValueException(String message) {
        super(message);
    }
}
