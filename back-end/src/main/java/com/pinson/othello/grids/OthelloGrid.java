package com.pinson.othello.grids;

import com.pinson.othello.commons.entities.grids.Grid;
import com.pinson.othello.commons.exceptions.NonPositiveValueException;
import com.pinson.othello.tiles.IOthelloTile;

import java.util.List;

public class OthelloGrid extends Grid<IOthelloTile> implements IOthelloGrid {

    List<IOthelloTile> tiles;


    public OthelloGrid() throws NonPositiveValueException {
        super(8, 8);
    }
}
