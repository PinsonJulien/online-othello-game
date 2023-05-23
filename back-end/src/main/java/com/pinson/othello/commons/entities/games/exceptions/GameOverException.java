package com.pinson.othello.commons.entities.games.exceptions;

public class GameOverException extends Exception {
    public GameOverException() {
        super("Game is over");
    }
}
