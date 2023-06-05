package com.pinson.othello.players.exceptions;

public class DuplicateUsernameException extends RuntimeException {
    public DuplicateUsernameException(String username) {
        super("The given username is already taken by another user: " + username);
    }
}
