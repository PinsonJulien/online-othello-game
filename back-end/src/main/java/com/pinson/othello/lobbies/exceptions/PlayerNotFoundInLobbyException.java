package com.pinson.othello.lobbies.exceptions;

public class PlayerNotFoundInLobbyException extends Exception {
    public PlayerNotFoundInLobbyException() {
        super("The player was not found inside the lobby.");
    }
}
