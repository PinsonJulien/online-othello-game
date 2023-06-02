package com.pinson.othello.games.exceptions;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException(Long id) {
        super("Could not find game " + id + ".");
    }
}
