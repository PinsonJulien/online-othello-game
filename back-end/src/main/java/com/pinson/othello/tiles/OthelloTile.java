package com.pinson.othello.tiles;

import com.pinson.othello.commons.entities.positions.MatrixPositions.IMatrixPosition;
import com.pinson.othello.commons.entities.tiles.Tile;
import com.pinson.othello.disks.IOthelloDisk;

public class OthelloTile extends Tile<IOthelloDisk> implements IOthelloTile {
    public OthelloTile(IMatrixPosition<Integer> position) {
        super(position);
    }
    
}
