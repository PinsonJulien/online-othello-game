package com.pinson.othello.players.exceptions;

import com.pinson.othello.players.IOthelloPlayer;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(Long id) {
        super("Could not find player with ID: " + id);
    }

    public PlayerNotFoundException(IOthelloPlayer player) {
        this(player.getId());
    }

    public PlayerNotFoundException(String username) {
        super("Could not find player with username: " + username);
    }
}
