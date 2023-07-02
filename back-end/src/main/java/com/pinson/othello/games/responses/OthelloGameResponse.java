package com.pinson.othello.games.responses;

import com.pinson.othello.games.OthelloGameStatus;

public class OthelloGameResponse {

    private Long id;
    private int gridWidth;
    private int gridHeight;

    private OthelloGameStatus


    protected class GridSize {
        private int width;
        private int height;

        protected GridSize(int width, int height) {
            this.setWidth(width);
            this.setHeight(height);
        }

        public int getWidth() {
            return this.width;
        }

        public GridSize setWidth(int width) {
            this.width = width;
            return this;
        }

        public int getHeight() {
            return this.height;
        }

        public GridSize setHeight(int height) {
            this.height = height;
            return this;
        }

    }

}
