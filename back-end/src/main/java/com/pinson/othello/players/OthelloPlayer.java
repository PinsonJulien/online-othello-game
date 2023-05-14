package com.pinson.othello.players;

import com.pinson.othello.commons.entities.players.Player;
import jakarta.persistence.*;

@Entity
public class OthelloPlayer extends Player implements IOthelloPlayer {

    @Id
    private Long id;
    private String username;
    private String password;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
