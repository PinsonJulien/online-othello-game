package com.pinson.othello.moves;

import jakarta.persistence.*;

@Entity
@Table(name = "moves")
public class Move {

    @Id
    private Long id;

    public Move() {
        //
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
