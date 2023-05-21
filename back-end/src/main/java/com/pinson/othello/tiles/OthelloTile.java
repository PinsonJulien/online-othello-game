package com.pinson.othello.tiles;

import com.pinson.othello.commons.entities.tiles.Tile;
import com.pinson.othello.disks.IOthelloDisk;
import com.pinson.othello.positions.IOthelloPosition;

public class OthelloTile extends Tile<IOthelloDisk, IOthelloTile> implements IOthelloTile {
    public OthelloTile(IOthelloPosition position) {
        super(position);
    }

    @Override
    public IOthelloPosition getPosition() {
        try {
            return IOthelloPosition.create(super.getPosition());
        } catch (Exception e) {
            return null;
        }
    }
}
