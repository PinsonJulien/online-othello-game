package com.pinson.othello.disks;

import com.pinson.othello.commons.entities.pieces.IPiece;
import com.pinson.othello.gamePlayers.IOthelloGamePlayer;
import com.pinson.othello.gamePlayers.OthelloGamePlayerColor;
import com.pinson.othello.positions.IOthelloPosition;
import com.pinson.othello.tiles.IOthelloTile;

public interface IOthelloDisk extends IPiece<IOthelloTile, IOthelloDisk> {

    static IOthelloDisk create(IOthelloGamePlayer gamePlayer) {
        return new OthelloDisk(gamePlayer);
    }

    IOthelloGamePlayer getGamePlayer();
    IOthelloDisk setGamePlayer(IOthelloGamePlayer gamePlayer);

    OthelloGamePlayerColor getColor();

    IOthelloPosition getPosition();

}
