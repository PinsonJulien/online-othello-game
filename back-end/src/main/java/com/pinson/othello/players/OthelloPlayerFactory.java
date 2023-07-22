package com.pinson.othello.players;

import org.springframework.stereotype.Component;

@Component
public class OthelloPlayerFactory {

    public OthelloPlayer create(String username, String password) {
        OthelloPlayer player = new OthelloPlayer();
        player.setUsername(username);
        player.setPassword(password);
        return player;
    }

    public OthelloPlayer create(IOthelloPlayer player) {
        OthelloPlayer newPlayer = new OthelloPlayer();
        newPlayer.setId(player.getId());
        newPlayer.setUsername(player.getUsername());
        newPlayer.setPassword(player.getPassword());
        newPlayer.setCreatedAt(player.getCreatedAt());
        newPlayer.setUpdatedAt(player.getUpdatedAt());

        return newPlayer;
    }

}
