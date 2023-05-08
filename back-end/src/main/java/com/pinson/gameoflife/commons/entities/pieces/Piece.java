package com.pinson.gameoflife.commons.entities.pieces;

import com.pinson.gameoflife.commons.entities.tiles.ITile;

public class Piece implements IPiece {
    private ITile cell;

    public Piece() {
        this.cell = null;
    }

    public Piece(ITile cell) {
        this.cell = cell;
    }

    @Override
    public ITile getCell() {
        return this.cell;
    }

    @Override
    public IPiece setCell(ITile cell) {
        this.cell = cell;
        if (this.cell.getPiece() != this)
            this.cell.setPiece(this);

        return this;
    }
}
