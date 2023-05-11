package com.pinson.othello.disks;

import com.pinson.othello.commons.entities.pieces.IPiece;
import com.pinson.othello.players.IOthelloPlayer;

public interface IOthelloDisk extends IPiece {

    IOthelloPlayer getOwner();

    static IOthelloDisk create(IOthelloPlayer owner) {
        return new OthelloDisk(owner);
    }
}
