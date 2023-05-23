package com.pinson.othello.lobbies.exceptions;

public class PlayerAlreadyInLobbyException extends Exception {
    public PlayerAlreadyInLobbyException() {
        super("The assigned player was already assigned to this lobby.");
    }
}
