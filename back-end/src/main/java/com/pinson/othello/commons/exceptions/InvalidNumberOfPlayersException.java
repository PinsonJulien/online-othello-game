package com.pinson.othello.commons.exceptions;

public class InvalidNumberOfPlayersException extends Exception {

    public InvalidNumberOfPlayersException() {
        super("Invalid number of players.");
    }

    public InvalidNumberOfPlayersException(String message) {
        super("Invalid number of players: " + message);
    }
}
