package com.pinson.othello.games;

import com.pinson.othello.commons.entities.grids.exceptions.GridSizeException;
import com.pinson.othello.commons.exceptions.InvalidNumberOfPlayersException;
import com.pinson.othello.commons.helpers.collections.matrixArrayLists.exceptions.MatrixIndexOutOfBoundsException;
import com.pinson.othello.gamePlayers.IOthelloGamePlayer;
import com.pinson.othello.gamePlayers.OthelloGamePlayer;
import com.pinson.othello.gamePlayers.OthelloGamePlayerColor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OthelloGameTest {

    @Test
    void constructor() {
        // This will be tested using JPA reflection.
    }

    @Test
    void constructor_gamePlayersWidthHeight() throws GridSizeException, InvalidNumberOfPlayersException, MatrixIndexOutOfBoundsException {
        IOthelloGame game = new OthelloGame();

        ArrayList<OthelloGamePlayer> gamePlayers = new ArrayList<>();

        gamePlayers.add((OthelloGamePlayer) IOthelloGamePlayer.create(null, OthelloGamePlayerColor.BLACK));
        gamePlayers.add((OthelloGamePlayer) IOthelloGamePlayer.create(null, OthelloGamePlayerColor.WHITE));
        gamePlayers.add((OthelloGamePlayer) IOthelloGamePlayer.create(null, OthelloGamePlayerColor.BLACK));
        gamePlayers.add((OthelloGamePlayer) IOthelloGamePlayer.create(null, OthelloGamePlayerColor.WHITE));
        gamePlayers.add((OthelloGamePlayer) IOthelloGamePlayer.create(null, OthelloGamePlayerColor.BLACK));
        gamePlayers.add((OthelloGamePlayer) IOthelloGamePlayer.create(null, OthelloGamePlayerColor.WHITE));

        /*
         * 2 Players
         *
         * B W
         * W B
         *
         */

        // Bare minimum
        game = IOthelloGame.create(gamePlayers.subList(0, 2), 4, 4);

        assertEquals(2, game.getGamePlayers().size());
        assertEquals(4, game.getGridWidth());
        assertEquals(4, game.getGridHeight());

        // check if the grid is properly initialized
        assertEquals(4, game.getAllDisks().size());
        assertEquals(12, game.getAllEmptyTiles().size());
        assertEquals(gamePlayers.get(0), game.getDiskAt(1, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(1, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(2, 1).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 2).getGamePlayer());

        // Optimal size
        game = IOthelloGame.create(gamePlayers.subList(0, 2), 8, 8);

        assertEquals(2, game.getGamePlayers().size());
        assertEquals(8, game.getGridWidth());
        assertEquals(8, game.getGridHeight());

        assertEquals(4, game.getAllDisks().size());
        assertEquals(60, game.getAllEmptyTiles().size());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 4).getGamePlayer());

        /*
         * 4 Players
         *
         * B W B W
         * W B W B
         * B W B W
         * W B W B
         *
         */

        // Bare minimum
        game = IOthelloGame.create(gamePlayers.subList(0, 4), 6, 6);

        assertEquals(4, game.getGamePlayers().size());
        assertEquals(6, game.getGridWidth());
        assertEquals(6, game.getGridHeight());

        assertEquals(16, game.getAllDisks().size());
        assertEquals(20, game.getAllEmptyTiles().size());
        assertEquals(gamePlayers.get(0), game.getDiskAt(1, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(1, 2).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(1, 3).getGamePlayer());
        assertEquals(gamePlayers.get(3), game.getDiskAt(1, 4).getGamePlayer());
        assertEquals(gamePlayers.get(3), game.getDiskAt(2, 1).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(2, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(2, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(3, 2).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(3), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(3), game.getDiskAt(4, 1).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(4, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 4).getGamePlayer());

        // 6 players = 6x6
        /**
         *
         * B W B W B W
         * W B W B W B
         * B W B W B W
         * W B W B W B
         * B W B W B W
         * W B W B W B
         *
         */

        // Bare minimum
        game = IOthelloGame.create(gamePlayers.subList(0, 6), 8, 8);

        assertEquals(6, game.getGamePlayers().size());
        assertEquals(8, game.getGridWidth());
        assertEquals(8, game.getGridHeight());

        assertEquals(36, game.getAllDisks().size());
        assertEquals(28, game.getAllEmptyTiles().size());
        assertEquals(gamePlayers.get(0), game.getDiskAt(1, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(1, 2).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(1, 3).getGamePlayer());
        assertEquals(gamePlayers.get(3), game.getDiskAt(1, 4).getGamePlayer());
        assertEquals(gamePlayers.get(4), game.getDiskAt(1, 5).getGamePlayer());
        assertEquals(gamePlayers.get(5), game.getDiskAt(1, 6).getGamePlayer());
        assertEquals(gamePlayers.get(5), game.getDiskAt(2, 1).getGamePlayer());
        assertEquals(gamePlayers.get(4), game.getDiskAt(2, 2).getGamePlayer());
        assertEquals(gamePlayers.get(3), game.getDiskAt(2, 3).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(2, 4).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(2, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 6).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(3, 2).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(3), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(4), game.getDiskAt(3, 5).getGamePlayer());
        assertEquals(gamePlayers.get(5), game.getDiskAt(3, 6).getGamePlayer());
        assertEquals(gamePlayers.get(5), game.getDiskAt(4, 1).getGamePlayer());
        assertEquals(gamePlayers.get(4), game.getDiskAt(4, 2).getGamePlayer());
        assertEquals(gamePlayers.get(3), game.getDiskAt(4, 3).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(4, 4).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 6).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(5, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 2).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(5, 3).getGamePlayer());
        assertEquals(gamePlayers.get(3), game.getDiskAt(5, 4).getGamePlayer());
        assertEquals(gamePlayers.get(4), game.getDiskAt(5, 5).getGamePlayer());
        assertEquals(gamePlayers.get(5), game.getDiskAt(5, 6).getGamePlayer());
        assertEquals(gamePlayers.get(5), game.getDiskAt(6, 1).getGamePlayer());
        assertEquals(gamePlayers.get(4), game.getDiskAt(6, 2).getGamePlayer());
        assertEquals(gamePlayers.get(3), game.getDiskAt(6, 3).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(6, 4).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(6, 6).getGamePlayer());

    }


    @Test
    void getId() {
    }

    @Test
    void setId() {
    }

    @Test
    void getStatus() {
    }

    @Test
    void setStatus() {
    }

    @Test
    void getWinner() {
    }

    @Test
    void setWinner() {
    }

    @Test
    void getGamePlayers() {
    }

    @Test
    void setGamePlayers() {
    }

    @Test
    void getMoves() {
    }

    @Test
    void setMoves() {
    }

    @Test
    void getCreatedAt() {
    }

    @Test
    void setCreatedAt() {
    }

    @Test
    void getUpdatedAt() {
    }

    @Test
    void setUpdatedAt() {
    }

    @Test
    void playMove() {
    }

    @Test
    void testPlayMove() {
    }

    @Test
    void skipMove() {
    }

    @Test
    void getCurrentTurnPlayer() {
    }

    @Test
    void getValidMoves() {
    }
}