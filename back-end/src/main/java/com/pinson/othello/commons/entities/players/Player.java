package com.pinson.othello.commons.entities.players;

public abstract class Player implements IPlayer {

    @Override
    public IPlayer clone() {
        try {
            return (IPlayer) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        IPlayer clone = (IPlayer) super.clone();
        return null;
    }

}
