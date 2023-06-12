package com.pinson.othello.players;

import org.springframework.stereotype.Component;

@Component
public class OthelloPlayerFactory {

    OthelloPlayer create(String name, String password) {
        OthelloPlayer player = new OthelloPlayer();
        player.setUsername(name);
        player.setPassword(password);
        return player;
    }

    OthelloPlayer create(IOthelloPlayer player) {
        OthelloPlayer newPlayer = new OthelloPlayer();
        newPlayer.setId(player.getId());
        newPlayer.setUsername(player.getUsername());
        newPlayer.setPassword(player.getPassword());
        newPlayer.setCreatedAt(player.getCreatedAt());
        newPlayer.setUpdatedAt(player.getUpdatedAt());

        return newPlayer;
    }

}
