package com.pinson.othello.commons.exceptions;

public class NonEvenNumberException extends Exception {
    public NonEvenNumberException() {
        super("The provided number is not even.");
    }

    public NonEvenNumberException(String message) {
        super(message);
    }

}
