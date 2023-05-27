package com.pinson.othello.lobbies.exceptions;

public class FullLobbyException extends Exception {
    public FullLobbyException() {
        super("Cannot add the user to the lobby because it's already at capacity.");
    }
}
