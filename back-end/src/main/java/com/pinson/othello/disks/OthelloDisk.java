package com.pinson.othello.disks;

import com.pinson.othello.commons.entities.pieces.Piece;
import com.pinson.othello.gamePlayers.IOthelloGamePlayer;
import com.pinson.othello.gamePlayers.OthelloGamePlayerColor;
import com.pinson.othello.tiles.IOthelloTile;

public class OthelloDisk extends Piece<IOthelloTile, IOthelloDisk> implements IOthelloDisk {
    private IOthelloGamePlayer gamePlayer;

    public OthelloDisk(IOthelloGamePlayer gamePlayer) {
        super();

        this.setGamePlayer(gamePlayer);
    }

    @Override
    public IOthelloGamePlayer getGamePlayer() {
        return this.gamePlayer;
    }

    @Override
    public IOthelloDisk setGamePlayer(IOthelloGamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;

        return this;
    }

    @Override
    public OthelloGamePlayerColor getColor() {
        return this.getGamePlayer().getPlayerColor();
    }

}
