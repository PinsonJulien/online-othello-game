package com.pinson.othello.disks;

import com.pinson.othello.commons.entities.pieces.Piece;
import com.pinson.othello.players.IOthelloPlayer;
import com.pinson.othello.tiles.IOthelloTile;

public class OthelloDisk extends Piece<IOthelloTile> implements IOthelloDisk {
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

    @Override
    public IOthelloTile getTile() {
        return super.getTile();
    }

    @Override
    public IOthelloDisk setTile(IOthelloTile cell) {
        super.setTile(cell);

        return null;
    }
}
