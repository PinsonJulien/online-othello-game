package com.pinson.othello.games;

import com.pinson.othello.commons.entities.grids.exceptions.GridSizeException;
import com.pinson.othello.commons.exceptions.InvalidMoveException;
import com.pinson.othello.commons.exceptions.InvalidNumberOfPlayersException;
import com.pinson.othello.commons.helpers.collections.matrixArrayLists.exceptions.MatrixIndexOutOfBoundsException;
import com.pinson.othello.gamePlayers.IOthelloGamePlayer;
import com.pinson.othello.gamePlayers.OthelloGamePlayer;
import com.pinson.othello.gamePlayers.OthelloGamePlayerColor;
import com.pinson.othello.moves.IOthelloMove;
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
        /*
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
    void isMoveValid() throws GridSizeException, InvalidNumberOfPlayersException {
        ArrayList<OthelloGamePlayer> gamePlayers = new ArrayList<>();
        IOthelloMove move = IOthelloMove.create();

        gamePlayers.add((OthelloGamePlayer) IOthelloGamePlayer.create(null, OthelloGamePlayerColor.BLACK));
        gamePlayers.add((OthelloGamePlayer) IOthelloGamePlayer.create(null, OthelloGamePlayerColor.WHITE));

        IOthelloGame game = IOthelloGame.create(gamePlayers, 6, 6);
        move.setGamePlayer(gamePlayers.get(0));

        // Valid moves
        assertTrue(game.isMoveValid(move.setRow(1).setColumn(3)));
        assertTrue(game.isMoveValid(move.setRow(2).setColumn(4)));
        assertTrue(game.isMoveValid(move.setRow(3).setColumn(1)));
        assertTrue(game.isMoveValid(move.setRow(4).setColumn(2)));

        // Invalid moves
        assertFalse(game.isMoveValid(move.setRow(0).setColumn(0)));
        assertFalse(game.isMoveValid(move.setRow(0).setColumn(1)));
        assertFalse(game.isMoveValid(move.setRow(0).setColumn(2)));
        assertFalse(game.isMoveValid(move.setRow(0).setColumn(3)));
        assertFalse(game.isMoveValid(move.setRow(0).setColumn(4)));
        assertFalse(game.isMoveValid(move.setRow(0).setColumn(5)));
        assertFalse(game.isMoveValid(move.setRow(1).setColumn(0)));
        assertFalse(game.isMoveValid(move.setRow(1).setColumn(1)));
        assertFalse(game.isMoveValid(move.setRow(1).setColumn(2)));
        assertFalse(game.isMoveValid(move.setRow(1).setColumn(4)));
        assertFalse(game.isMoveValid(move.setRow(1).setColumn(5)));
        assertFalse(game.isMoveValid(move.setRow(2).setColumn(0)));
        assertFalse(game.isMoveValid(move.setRow(2).setColumn(1)));
        assertFalse(game.isMoveValid(move.setRow(2).setColumn(2)));
        assertFalse(game.isMoveValid(move.setRow(2).setColumn(3)));
        assertFalse(game.isMoveValid(move.setRow(2).setColumn(5)));
        assertFalse(game.isMoveValid(move.setRow(3).setColumn(0)));
        assertFalse(game.isMoveValid(move.setRow(3).setColumn(2)));
        assertFalse(game.isMoveValid(move.setRow(3).setColumn(3)));
        assertFalse(game.isMoveValid(move.setRow(3).setColumn(4)));
        assertFalse(game.isMoveValid(move.setRow(3).setColumn(5)));
        assertFalse(game.isMoveValid(move.setRow(4).setColumn(0)));
        assertFalse(game.isMoveValid(move.setRow(4).setColumn(1)));
        assertFalse(game.isMoveValid(move.setRow(4).setColumn(3)));
        assertFalse(game.isMoveValid(move.setRow(4).setColumn(4)));
        assertFalse(game.isMoveValid(move.setRow(4).setColumn(5)));
        assertFalse(game.isMoveValid(move.setRow(5).setColumn(0)));
        assertFalse(game.isMoveValid(move.setRow(5).setColumn(1)));
        assertFalse(game.isMoveValid(move.setRow(5).setColumn(2)));
        assertFalse(game.isMoveValid(move.setRow(5).setColumn(3)));
        assertFalse(game.isMoveValid(move.setRow(5).setColumn(4)));
        assertFalse(game.isMoveValid(move.setRow(5).setColumn(5)));
    }

    @Test
    void playMove() throws GridSizeException, InvalidNumberOfPlayersException, InvalidMoveException, MatrixIndexOutOfBoundsException {
        /*            0 1 2 3 4 5 6 7
         *          0 x x x x x x x x
         *          1 x x x x x x x x
         *          2 x x B W B W x x
         *          3 x x W B W B x x
         *          4 x x B W B W x x
         *          5 x x W B W B x x
         *          6 x x x x x x x x
         *          7 x x x x x x x x
         */
        ArrayList<OthelloGamePlayer> gamePlayers = new ArrayList<>();
        gamePlayers.add((OthelloGamePlayer) IOthelloGamePlayer.create(null, OthelloGamePlayerColor.BLACK).setId(1L));
        gamePlayers.add((OthelloGamePlayer) IOthelloGamePlayer.create(null, OthelloGamePlayerColor.WHITE).setId(2L));
        gamePlayers.add((OthelloGamePlayer) IOthelloGamePlayer.create(null, OthelloGamePlayerColor.BLACK).setId(3L));
        gamePlayers.add((OthelloGamePlayer) IOthelloGamePlayer.create(null, OthelloGamePlayerColor.WHITE).setId(4L));

        IOthelloGame game = IOthelloGame.create(gamePlayers, 8, 8);
        IOthelloMove move = IOthelloMove.create().setGamePlayer(gamePlayers.get(0)).setRow(3).setColumn(1);
        /*            0 1 2 3 4 5 6 7
         *          0 x x x x x x x x
         *          1 x x x x x x x x
         *          2 x x B W B W x x
         *          3 x B B B W B x x
         *          4 x x B W B W x x
         *          5 x x W B W B x x
         *          6 x x x x x x x x
         *          7 x x x x x x x x
         */
        game.playMove(move);
        assertEquals(1, game.getMoves().size());
        assertEquals(17, game.getAllDisks().size());
        // Inserted
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 1).getGamePlayer());
        // Flipped
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 2).getGamePlayer());
        // Unchanged
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(2, 3).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(2, 4).getGamePlayer());
        assertEquals(gamePlayers.get(3), game.getDiskAt(2, 5).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 3).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(4, 4).getGamePlayer());
        assertEquals(gamePlayers.get(3), game.getDiskAt(4, 5).getGamePlayer());
        assertEquals(gamePlayers.get(3), game.getDiskAt(5, 2).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(5, 3).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(5, 5).getGamePlayer());

        // Second move
        move = IOthelloMove.create().setGamePlayer(gamePlayers.get(1)).setRow(2).setColumn(1);
        /*            0 1 2 3 4 5 6 7
         *          0 x x x x x x x x
         *          1 x x x x x x x x
         *          2 x W W W B W x x
         *          3 x B W B W B x x
         *          4 x x B W B W x x
         *          5 x x W B W B x x
         *          6 x x x x x x x x
         *          7 x x x x x x x x
         */
        game.playMove(move);
        assertEquals(2, game.getMoves().size());
        assertEquals(18, game.getAllDisks().size());
        // Inserted
        assertEquals(gamePlayers.get(1), game.getDiskAt(2, 1).getGamePlayer());
        // Flipped
        assertEquals(gamePlayers.get(1), game.getDiskAt(2, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(3, 2).getGamePlayer());
        // Unchanged
        assertEquals(gamePlayers.get(1), game.getDiskAt(2, 3).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(2, 4).getGamePlayer());
        assertEquals(gamePlayers.get(3), game.getDiskAt(2, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 1).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 3).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(4, 4).getGamePlayer());
        assertEquals(gamePlayers.get(3), game.getDiskAt(4, 5).getGamePlayer());
        assertEquals(gamePlayers.get(3), game.getDiskAt(5, 2).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(5, 3).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(5, 5).getGamePlayer());

        // Third move
        move = IOthelloMove.create().setGamePlayer(gamePlayers.get(2)).setRow(2).setColumn(0);
        /*            0 1 2 3 4 5 6 7
         *          0 x x x x x x x x
         *          1 x x x x x x x x
         *          2 B B B B B W x x
         *          3 x B W B W B x x
         *          4 x x B W B W x x
         *          5 x x W B W B x x
         *          6 x x x x x x x x
         *          7 x x x x x x x x
         */
        game.playMove(move);
        assertEquals(3, game.getMoves().size());
        assertEquals(19, game.getAllDisks().size());
        // Inserted
        assertEquals(gamePlayers.get(2), game.getDiskAt(2, 0).getGamePlayer());
        // Flipped
        assertEquals(gamePlayers.get(2), game.getDiskAt(2, 1).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(2, 2).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(2, 3).getGamePlayer());
        // Unchanged
        assertEquals(gamePlayers.get(2), game.getDiskAt(2, 4).getGamePlayer());
        assertEquals(gamePlayers.get(3), game.getDiskAt(2, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(3, 2).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 3).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(4, 4).getGamePlayer());
        assertEquals(gamePlayers.get(3), game.getDiskAt(4, 5).getGamePlayer());
        assertEquals(gamePlayers.get(3), game.getDiskAt(5, 2).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(5, 3).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(5, 5).getGamePlayer());

        // Fourth move
        move = IOthelloMove.create().setGamePlayer(gamePlayers.get(3)).setRow(1).setColumn(2);
        /*            0 1 2 3 4 5 6 7
         *          0 x x x x x x x x
         *          1 x x W x x x x x
         *          2 B B W W B W x x
         *          3 x B W B W B x x
         *          4 x x B W B W x x
         *          5 x x W B W B x x
         *          6 x x x x x x x x
         *          7 x x x x x x x x
         */
        game.playMove(move);
        assertEquals(4, game.getMoves().size());
        assertEquals(20, game.getAllDisks().size());
        // Inserted
        assertEquals(gamePlayers.get(3), game.getDiskAt(1, 2).getGamePlayer());
        // Flipped
        assertEquals(gamePlayers.get(3), game.getDiskAt(2, 2).getGamePlayer());
        assertEquals(gamePlayers.get(3), game.getDiskAt(2, 3).getGamePlayer());
        // Unchanged
        assertEquals(gamePlayers.get(2), game.getDiskAt(2, 0).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(2, 1).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(2, 4).getGamePlayer());
        assertEquals(gamePlayers.get(3), game.getDiskAt(2, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(3, 2).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 3).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(4, 4).getGamePlayer());
        assertEquals(gamePlayers.get(3), game.getDiskAt(4, 5).getGamePlayer());
        assertEquals(gamePlayers.get(3), game.getDiskAt(5, 2).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(5, 3).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(5, 5).getGamePlayer());

        // Fifth move
        move = IOthelloMove.create().setGamePlayer(gamePlayers.get(0)).setRow(1).setColumn(3);
        /*            0 1 2 3 4 5 6 7
         *          0 x x x x x x x x
         *          1 x x W B x x x x
         *          2 B B B B B W x x
         *          3 x B W B W B x x
         *          4 x x B W B W x x
         *          5 x x W B W B x x
         *          6 x x x x x x x x
         *          7 x x x x x x x x
         */
        game.playMove(move);
        assertEquals(5, game.getMoves().size());
        assertEquals(21, game.getAllDisks().size());
        // Inserted
        assertEquals(gamePlayers.get(0), game.getDiskAt(1, 3).getGamePlayer());
        // Flipped
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 3).getGamePlayer());
        // Unchanged
        assertEquals(gamePlayers.get(3), game.getDiskAt(1, 2).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(2, 0).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(2, 1).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(2, 4).getGamePlayer());
        assertEquals(gamePlayers.get(3), game.getDiskAt(2, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(3, 2).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 3).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(4, 4).getGamePlayer());
        assertEquals(gamePlayers.get(3), game.getDiskAt(4, 5).getGamePlayer());
        assertEquals(gamePlayers.get(3), game.getDiskAt(5, 2).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(5, 3).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(5, 5).getGamePlayer());

        // The complex grid has been tested, now test the simple grid with a few moves till a player has to skip
        // D3 E3 F3 C5 D6 C3 C4 C7 C6 B6 A7 A6 D7 E8 F5 A8 B3 E6 B8 B7 B5 C8 F7 A5 D8 A4 F8 G8
        // A = 0
        // Letter = Column
        game = IOthelloGame.create(gamePlayers.subList(0, 2), 8, 8);
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(0)).setRow(3).setColumn(3));
        IOthelloMove move = IOthelloMove.create().setGamePlayer(gamePlayers.get(0)).setRow(3).setColumn(1);



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
    void skipMove() {
    }

    @Test
    void getCurrentTurnPlayer() {
    }

    @Test
    void getValidMoves() {
    }
}