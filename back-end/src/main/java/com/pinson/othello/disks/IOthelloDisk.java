package com.pinson.othello.disks;

import com.pinson.othello.commons.entities.pieces.IPiece;
import com.pinson.othello.players.IOthelloPlayer;
import com.pinson.othello.tiles.IOthelloTile;

public interface IOthelloDisk extends IPiece<IOthelloTile> {

    IOthelloPlayer getOwner();

    static OthelloDisk create(IOthelloPlayer owner) {
        return new OthelloDisk(owner);
    }

    @Override
    IOthelloDisk setTile(IOthelloTile tile);
}
