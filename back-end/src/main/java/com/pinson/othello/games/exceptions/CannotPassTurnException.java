package com.pinson.othello.games.exceptions;

public class CannotPassTurnException extends Exception {

    public CannotPassTurnException() {
        super("Cannot pass the turn");
    }

    public CannotPassTurnException(String message) {
        super("Cannot pass the turn: " + message);
    }

}
