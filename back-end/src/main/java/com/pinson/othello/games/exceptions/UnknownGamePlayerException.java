package com.pinson.othello.games.exceptions;

public class UnknownGamePlayerException extends Exception {
    public UnknownGamePlayerException() {
        super("Unknown player");
    }

    public UnknownGamePlayerException(String message) {
        super("Unknown player: " + message);
    }
}
