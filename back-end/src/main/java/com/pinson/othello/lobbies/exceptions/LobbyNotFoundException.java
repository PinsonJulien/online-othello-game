package com.pinson.othello.lobbies.exceptions;

public class LobbyNotFoundException extends RuntimeException {
    public LobbyNotFoundException(Long id) {
        super("Could not find lobby " + id + ".");
    }
}
