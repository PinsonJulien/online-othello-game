package com.pinson.othello.lobbies.exceptions;

public class PlayerNotFoundException extends Exception {
    public PlayerNotFoundException() {
        super("The player was not found inside the lobby.");
    }
}
