package com.pinson.othello.commons.exceptions;

public class InvalidMoveException extends Exception {

    public InvalidMoveException() {
        super("Invalid move exception");
    }

    public InvalidMoveException(String message) {
        super("Invalid move exception: " + message);
    }

}
