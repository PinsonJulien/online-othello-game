package com.pinson.gameoflife.commons.exceptions;

public class NonPositiveValueException extends Exception {

    public NonPositiveValueException() {
        super("The value must be positive.");
    }

    public NonPositiveValueException(String message) {
        super(message);
    }
}
