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

}
