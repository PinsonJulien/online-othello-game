package com.pinson.othello.commons.entities.players;

public abstract class Player implements IPlayer {

    public Player() {
        super();
    }

    protected Player(Player player) {
        super();
    }

    abstract public IPlayer copy();
}
