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
    void constructor() throws GridSizeException, InvalidNumberOfPlayersException, MatrixIndexOutOfBoundsException {
        IOthelloGame game = new OthelloGame();

        ArrayList<OthelloGamePlayer> gamePlayers = new ArrayList<>();

        gamePlayers.add((OthelloGamePlayer) IOthelloGamePlayer.create(null, OthelloGamePlayerColor.BLACK));
        gamePlayers.add((OthelloGamePlayer) IOthelloGamePlayer.create(null, OthelloGamePlayerColor.WHITE));
        gamePlayers.add((OthelloGamePlayer) IOthelloGamePlayer.create(null, OthelloGamePlayerColor.BLACK));
        gamePlayers.add((OthelloGamePlayer) IOthelloGamePlayer.create(null, OthelloGamePlayerColor.WHITE));
        gamePlayers.add((OthelloGamePlayer) IOthelloGamePlayer.create(null, OthelloGamePlayerColor.BLACK));
        gamePlayers.add((OthelloGamePlayer) IOthelloGamePlayer.create(null, OthelloGamePlayerColor.WHITE));


        // tests if the game is created with the correct grid size

        // Tests if the game is created with the correct number of players

        // tests if the game grid is properly initialized


        /*
         * 2 Players
         *
         * B W
         * W B
         *
         */

        game = IOthelloGame.create(gamePlayers.subList(0, 2), 6, 6);

        assertEquals(2, game.getGamePlayers().size());
        assertEquals(6, game.getGrid().getRows());
        assertEquals(6, game.getGrid().getColumns());

        // check if the grid is properly initialized
        assertEquals(gamePlayers.get(0), game.getGrid().getDiskAt(2, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getGrid().getDiskAt(2, 3).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getGrid().getDiskAt(3, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getGrid().getDiskAt(3, 3).getGamePlayer());

        // 4 players = 4x4
        /**
         *
         * B W B W
         * W B W B
         * B W B W
         * W B W B
         *
         */

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