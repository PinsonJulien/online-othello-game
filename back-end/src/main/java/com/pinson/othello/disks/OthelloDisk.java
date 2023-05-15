package com.pinson.othello.disks;

import com.pinson.othello.commons.entities.pieces.Piece;
import com.pinson.othello.players.IOthelloPlayer;

public class OthelloDisk extends Piece implements IOthelloDisk {

    private Long id;

    private IOthelloPlayer owner;

    public OthelloDisk(IOthelloPlayer owner) {
        this.setOwner(owner);
    }

    @Override
    public IOthelloPlayer getOwner() {
        return this.owner;
    }

    protected IOthelloDisk setOwner(IOthelloPlayer owner) {
        this.owner = owner;

        return this;
    }
}
