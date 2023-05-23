package com.pinson.othello.commons.entities.grids.exceptions;

public class GridSizeException extends Exception {
        public GridSizeException() {
            super("Invalid grid size.");
        }

        public GridSizeException(String message) {
            super("Invalid grid size: " + message);
        }
}
