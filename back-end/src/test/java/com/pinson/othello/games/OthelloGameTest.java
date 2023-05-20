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
         * W B
         * B W
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
        assertEquals(gamePlayers.get(1), game.getDiskAt(1, 1).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(1, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(2, 2).getGamePlayer());

        // Optimal size
        game = IOthelloGame.create(gamePlayers.subList(0, 2), 8, 8);

        assertEquals(2, game.getGamePlayers().size());
        assertEquals(8, game.getGridWidth());
        assertEquals(8, game.getGridHeight());

        assertEquals(4, game.getAllDisks().size());
        assertEquals(60, game.getAllEmptyTiles().size());
        assertEquals(gamePlayers.get(1), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 3).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 4).getGamePlayer());

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
        assertEquals(gamePlayers.get(3), game.getDiskAt(1, 1).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(1, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(1, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(1, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(2, 2).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(2, 3).getGamePlayer());
        assertEquals(gamePlayers.get(3), game.getDiskAt(2, 4).getGamePlayer());
        assertEquals(gamePlayers.get(3), game.getDiskAt(3, 1).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(3, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 2).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(4, 3).getGamePlayer());
        assertEquals(gamePlayers.get(3), game.getDiskAt(4, 4).getGamePlayer());

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
        assertEquals(gamePlayers.get(5), game.getDiskAt(1, 1).getGamePlayer());
        assertEquals(gamePlayers.get(4), game.getDiskAt(1, 2).getGamePlayer());
        assertEquals(gamePlayers.get(3), game.getDiskAt(1, 3).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(1, 4).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(1, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(1, 6).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(2, 2).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(2, 3).getGamePlayer());
        assertEquals(gamePlayers.get(3), game.getDiskAt(2, 4).getGamePlayer());
        assertEquals(gamePlayers.get(4), game.getDiskAt(2, 5).getGamePlayer());
        assertEquals(gamePlayers.get(5), game.getDiskAt(2, 6).getGamePlayer());
        assertEquals(gamePlayers.get(5), game.getDiskAt(3, 1).getGamePlayer());
        assertEquals(gamePlayers.get(4), game.getDiskAt(3, 2).getGamePlayer());
        assertEquals(gamePlayers.get(3), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(3, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 6).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 2).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(4, 3).getGamePlayer());
        assertEquals(gamePlayers.get(3), game.getDiskAt(4, 4).getGamePlayer());
        assertEquals(gamePlayers.get(4), game.getDiskAt(4, 5).getGamePlayer());
        assertEquals(gamePlayers.get(5), game.getDiskAt(4, 6).getGamePlayer());
        assertEquals(gamePlayers.get(5), game.getDiskAt(5, 1).getGamePlayer());
        assertEquals(gamePlayers.get(4), game.getDiskAt(5, 2).getGamePlayer());
        assertEquals(gamePlayers.get(3), game.getDiskAt(5, 3).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(5, 4).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(5, 6).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(6, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 2).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(6, 3).getGamePlayer());
        assertEquals(gamePlayers.get(3), game.getDiskAt(6, 4).getGamePlayer());
        assertEquals(gamePlayers.get(4), game.getDiskAt(6, 5).getGamePlayer());
        assertEquals(gamePlayers.get(5), game.getDiskAt(6, 6).getGamePlayer());
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
        assertTrue(game.isMoveValid(move.setRow(1).setColumn(2)));
        assertTrue(game.isMoveValid(move.setRow(2).setColumn(1)));
        assertTrue(game.isMoveValid(move.setRow(3).setColumn(4)));
        assertTrue(game.isMoveValid(move.setRow(4).setColumn(3)));

        // Invalid moves
        assertFalse(game.isMoveValid(move.setRow(0).setColumn(0)));
        assertFalse(game.isMoveValid(move.setRow(0).setColumn(1)));
        assertFalse(game.isMoveValid(move.setRow(0).setColumn(2)));
        assertFalse(game.isMoveValid(move.setRow(0).setColumn(3)));
        assertFalse(game.isMoveValid(move.setRow(0).setColumn(4)));
        assertFalse(game.isMoveValid(move.setRow(0).setColumn(5)));
        assertFalse(game.isMoveValid(move.setRow(1).setColumn(0)));
        assertFalse(game.isMoveValid(move.setRow(1).setColumn(1)));
        assertFalse(game.isMoveValid(move.setRow(1).setColumn(3)));
        assertFalse(game.isMoveValid(move.setRow(1).setColumn(4)));
        assertFalse(game.isMoveValid(move.setRow(1).setColumn(5)));
        assertFalse(game.isMoveValid(move.setRow(2).setColumn(0)));
        assertFalse(game.isMoveValid(move.setRow(2).setColumn(2)));
        assertFalse(game.isMoveValid(move.setRow(2).setColumn(3)));
        assertFalse(game.isMoveValid(move.setRow(2).setColumn(4)));
        assertFalse(game.isMoveValid(move.setRow(2).setColumn(5)));
        assertFalse(game.isMoveValid(move.setRow(3).setColumn(0)));
        assertFalse(game.isMoveValid(move.setRow(3).setColumn(1)));
        assertFalse(game.isMoveValid(move.setRow(3).setColumn(2)));
        assertFalse(game.isMoveValid(move.setRow(3).setColumn(3)));
        assertFalse(game.isMoveValid(move.setRow(3).setColumn(5)));
        assertFalse(game.isMoveValid(move.setRow(4).setColumn(0)));
        assertFalse(game.isMoveValid(move.setRow(4).setColumn(1)));
        assertFalse(game.isMoveValid(move.setRow(4).setColumn(2)));
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
         *          2 x x W B W B x x
         *          3 x x B W B W x x
         *          4 x x W B W B x x
         *          5 x x B W B W x x
         *          6 x x x x x x x x
         *          7 x x x x x x x x
         */
        ArrayList<OthelloGamePlayer> gamePlayers = new ArrayList<>();
        gamePlayers.add((OthelloGamePlayer) IOthelloGamePlayer.create(null, OthelloGamePlayerColor.BLACK).setId(1L));
        gamePlayers.add((OthelloGamePlayer) IOthelloGamePlayer.create(null, OthelloGamePlayerColor.WHITE).setId(2L));
        gamePlayers.add((OthelloGamePlayer) IOthelloGamePlayer.create(null, OthelloGamePlayerColor.BLACK).setId(3L));
        gamePlayers.add((OthelloGamePlayer) IOthelloGamePlayer.create(null, OthelloGamePlayerColor.WHITE).setId(4L));

        IOthelloGame game = IOthelloGame.create(gamePlayers, 8, 8);
        IOthelloMove move = IOthelloMove.create().setGamePlayer(gamePlayers.get(0)).setRow(3).setColumn(6);
        /*            0 1 2 3 4 5 6 7
         *          0 x x x x x x x x
         *          1 x x x x x x x x
         *          2 x x W B W B x x
         *          3 x x B W B B B x
         *          4 x x W B W B x x
         *          5 x x B W B W x x
         *          6 x x x x x x x x
         *          7 x x x x x x x x
         */
        game.playMove(move);
        assertEquals(1, game.getMoves().size());
        assertEquals(17, game.getAllDisks().size());
        assertEquals(game, move.getGame());

        // Ownership check
        assertEquals(gamePlayers.get(3), game.getDiskAt(2, 2).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(2, 3).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(2, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 5).getGamePlayer());

        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 5).getGamePlayer()); // Flipped
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 6).getGamePlayer()); // Inserted

        assertEquals(gamePlayers.get(3), game.getDiskAt(4, 2).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(4, 3).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 5).getGamePlayer());

        assertEquals(gamePlayers.get(0), game.getDiskAt(5, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 3).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(5, 4).getGamePlayer());
        assertEquals(gamePlayers.get(3), game.getDiskAt(5, 5).getGamePlayer());

        // Second move
        move = IOthelloMove.create().setGamePlayer(gamePlayers.get(1)).setRow(2).setColumn(6);
        /*            0 1 2 3 4 5 6 7
         *          0 x x x x x x x x
         *          1 x x x x x x x x
         *          2 x x W B W W W x
         *          3 x x B W B W B x
         *          4 x x W B W B x x
         *          5 x x B W B W x x
         *          6 x x x x x x x x
         *          7 x x x x x x x x
         */
        game.playMove(move);
        assertEquals(2, game.getMoves().size());
        assertEquals(18, game.getAllDisks().size());

        // Ownership check
        assertEquals(gamePlayers.get(3), game.getDiskAt(2, 2).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(2, 3).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(2, 4).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(2, 5).getGamePlayer()); // Flipped
        assertEquals(gamePlayers.get(1), game.getDiskAt(2, 6).getGamePlayer()); // Inserted

        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(3, 5).getGamePlayer()); // Flipped
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 6).getGamePlayer());

        assertEquals(gamePlayers.get(3), game.getDiskAt(4, 2).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(4, 3).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 5).getGamePlayer());

        assertEquals(gamePlayers.get(0), game.getDiskAt(5, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 3).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(5, 4).getGamePlayer());
        assertEquals(gamePlayers.get(3), game.getDiskAt(5, 5).getGamePlayer());

        // Third move
        move = IOthelloMove.create().setGamePlayer(gamePlayers.get(2)).setRow(2).setColumn(7);
        /*            0 1 2 3 4 5 6 7
         *          0 x x x x x x x x
         *          1 x x x x x x x x
         *          2 x x W B B B B B
         *          3 x x B W B W B x
         *          4 x x W B W B x x
         *          5 x x B W B W x x
         *          6 x x x x x x x x
         *          7 x x x x x x x x
         */
        game.playMove(move);
        assertEquals(3, game.getMoves().size());
        assertEquals(19, game.getAllDisks().size());

        // Ownership check

        assertEquals(gamePlayers.get(3), game.getDiskAt(2, 2).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(2, 3).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(2, 4).getGamePlayer()); // Flipped
        assertEquals(gamePlayers.get(2), game.getDiskAt(2, 5).getGamePlayer()); // Flipped
        assertEquals(gamePlayers.get(2), game.getDiskAt(2, 6).getGamePlayer()); // Flipped
        assertEquals(gamePlayers.get(2), game.getDiskAt(2, 7).getGamePlayer()); // Inserted

        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(3, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 6).getGamePlayer());

        assertEquals(gamePlayers.get(3), game.getDiskAt(4, 2).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(4, 3).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 5).getGamePlayer());

        assertEquals(gamePlayers.get(0), game.getDiskAt(5, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 3).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(5, 4).getGamePlayer());
        assertEquals(gamePlayers.get(3), game.getDiskAt(5, 5).getGamePlayer());

        // Fourth move
        move = IOthelloMove.create().setGamePlayer(gamePlayers.get(3)).setRow(1).setColumn(5);
        /*            0 1 2 3 4 5 6 7
         *          0 x x x x x x x x
         *          1 x x x x x W x x
         *          2 x x W B W W B B
         *          3 x x B W B W B x
         *          4 x x W B W B x x
         *          5 x x B W B W x x
         *          6 x x x x x x x x
         *          7 x x x x x x x x
         */
        game.playMove(move);
        assertEquals(4, game.getMoves().size());
        assertEquals(20, game.getAllDisks().size());

        // Ownership check
        assertEquals(gamePlayers.get(3), game.getDiskAt(1, 5).getGamePlayer()); // Inserted

        assertEquals(gamePlayers.get(3), game.getDiskAt(2, 2).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(2, 3).getGamePlayer());
        assertEquals(gamePlayers.get(3), game.getDiskAt(2, 4).getGamePlayer()); // Flipped
        assertEquals(gamePlayers.get(3), game.getDiskAt(2, 5).getGamePlayer()); // Flipped
        assertEquals(gamePlayers.get(2), game.getDiskAt(2, 6).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(2, 7).getGamePlayer());

        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(3, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 6).getGamePlayer());

        assertEquals(gamePlayers.get(3), game.getDiskAt(4, 2).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(4, 3).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 5).getGamePlayer());

        assertEquals(gamePlayers.get(0), game.getDiskAt(5, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 3).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(5, 4).getGamePlayer());
        assertEquals(gamePlayers.get(3), game.getDiskAt(5, 5).getGamePlayer());

        // Fifth move
        move = IOthelloMove.create().setGamePlayer(gamePlayers.get(0)).setRow(1).setColumn(4);
        /*            0 1 2 3 4 5 6 7
         *          0 x x x x x x x x
         *          1 x x x x B W x x
         *          2 x x W B B B B B
         *          3 x x B W B W B x
         *          4 x x W B W B x x
         *          5 x x B W B W x x
         *          6 x x x x x x x x
         *          7 x x x x x x x x
         */
        game.playMove(move);
        assertEquals(5, game.getMoves().size());
        assertEquals(21, game.getAllDisks().size());

        // Ownership check
        assertEquals(gamePlayers.get(0), game.getDiskAt(1, 4).getGamePlayer()); // Inserted
        assertEquals(gamePlayers.get(3), game.getDiskAt(1, 5).getGamePlayer());

        assertEquals(gamePlayers.get(3), game.getDiskAt(2, 2).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(2, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 4).getGamePlayer()); // Flipped
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 5).getGamePlayer()); // Flipped
        assertEquals(gamePlayers.get(2), game.getDiskAt(2, 6).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(2, 7).getGamePlayer());

        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(3, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 6).getGamePlayer());

        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(3, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 6).getGamePlayer());

        assertEquals(gamePlayers.get(3), game.getDiskAt(4, 2).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(4, 3).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 5).getGamePlayer());

        assertEquals(gamePlayers.get(0), game.getDiskAt(5, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 3).getGamePlayer());
        assertEquals(gamePlayers.get(2), game.getDiskAt(5, 4).getGamePlayer());
        assertEquals(gamePlayers.get(3), game.getDiskAt(5, 5).getGamePlayer());

        // The complex grid has been tested, now test the simple grid with a few moves till a player has to skip
        // Final grid
        /*            0 1 2 3 4 5 6 7
         *          0 x x x x x x x x
         *          1 x x x x x x x x
         *          2 x B B B B B x x
         *          3 W x B B B x x x
         *          4 W W B B B B x x
         *          5 W W W B B x x x
         *          6 W W W B x B x x
         *          7 W W W W W W W x
         */
        // moves: D3 E3 F3 C5 D6 C3 C4 C7 C6 B6 A7 A6 D7 E8 F5 A8 B3 E6 B8 B7 B5 C8 F7 A5 D8 A4 F8 G8

        game = IOthelloGame.create(gamePlayers.subList(0, 2), 8, 8);
        // D3
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(0)).setRow(2).setColumn(3));
        assertEquals(1, game.getMoves().size());
        assertEquals(5, game.getAllDisks().size());
        // Black
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 3).getGamePlayer());
        // White
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 4).getGamePlayer());

        // E3
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(1)).setRow(2).setColumn(4));
        assertEquals(2, game.getMoves().size());
        assertEquals(6, game.getAllDisks().size());
        // Black
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 3).getGamePlayer());
        // White
        assertEquals(gamePlayers.get(1), game.getDiskAt(2, 4).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 4).getGamePlayer());

        // F3
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(0)).setRow(2).setColumn(5));
        assertEquals(3, game.getMoves().size());
        assertEquals(7, game.getAllDisks().size());
        // Black
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 3).getGamePlayer());
        // White
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 4).getGamePlayer());

        // C5
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(1)).setRow(4).setColumn(2));
        assertEquals(4, game.getMoves().size());
        assertEquals(8, game.getAllDisks().size());
        // Black
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 4).getGamePlayer());
        // White
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 3).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 4).getGamePlayer());

        // D6
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(0)).setRow(5).setColumn(3));
        assertEquals(5, game.getMoves().size());
        assertEquals(9, game.getAllDisks().size());
        // Black
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(5, 3).getGamePlayer());
        // White
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 4).getGamePlayer());

        // C3
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(1)).setRow(2).setColumn(2));
        assertEquals(6, game.getMoves().size());
        assertEquals(10, game.getAllDisks().size());
        // Black
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(5, 3).getGamePlayer());
        // White
        assertEquals(gamePlayers.get(1), game.getDiskAt(2, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 4).getGamePlayer());

        // C4
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(0)).setRow(3).setColumn(2));
        assertEquals(7, game.getMoves().size());
        assertEquals(11, game.getAllDisks().size());
        // Black
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(5, 3).getGamePlayer());
        // White
        assertEquals(gamePlayers.get(1), game.getDiskAt(2, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 4).getGamePlayer());

        // C7
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(1)).setRow(6).setColumn(2));
        assertEquals(8, game.getMoves().size());
        assertEquals(12, game.getAllDisks().size());
        // Black
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 3).getGamePlayer());
        // White
        assertEquals(gamePlayers.get(1), game.getDiskAt(2, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 4).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 3).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 2).getGamePlayer());

        // C6
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(0)).setRow(5).setColumn(2));
        assertEquals(9, game.getMoves().size());
        assertEquals(13, game.getAllDisks().size());
        // Black
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(5, 2).getGamePlayer());
        // White
        assertEquals(gamePlayers.get(1), game.getDiskAt(2, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 4).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 3).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 2).getGamePlayer());

        // B6
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(1)).setRow(5).setColumn(1));
        assertEquals(10, game.getMoves().size());
        assertEquals(14, game.getAllDisks().size());
        // Black
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 3).getGamePlayer());
        // White
        assertEquals(gamePlayers.get(1), game.getDiskAt(2, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 4).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 3).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 2).getGamePlayer());

        // A7
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(0)).setRow(6).setColumn(0));
        assertEquals(11, game.getMoves().size());
        assertEquals(15, game.getAllDisks().size());
        // Black
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(5, 1).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(6, 0).getGamePlayer());
        // White
        assertEquals(gamePlayers.get(1), game.getDiskAt(2, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 4).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 3).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 2).getGamePlayer());

        // A6
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(1)).setRow(5).setColumn(0));
        assertEquals(12, game.getMoves().size());
        assertEquals(16, game.getAllDisks().size());
        // Black
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(6, 0).getGamePlayer());
        // White
        assertEquals(gamePlayers.get(1), game.getDiskAt(2, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 4).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 3).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 2).getGamePlayer());

        // D7
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(0)).setRow(6).setColumn(3));
        assertEquals(13, game.getMoves().size());
        assertEquals(17, game.getAllDisks().size());
        // Black
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(5, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(6, 0).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(6, 3).getGamePlayer());
        // White
        assertEquals(gamePlayers.get(1), game.getDiskAt(2, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 4).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 2).getGamePlayer());

        // E8
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(1)).setRow(7).setColumn(4));
        assertEquals(14, game.getMoves().size());
        assertEquals(18, game.getAllDisks().size());
        // Black
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(5, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(6, 0).getGamePlayer());
        // White
        assertEquals(gamePlayers.get(1), game.getDiskAt(2, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 4).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 3).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 4).getGamePlayer());

        // F5
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(0)).setRow(4).setColumn(5));
        assertEquals(15, game.getMoves().size());
        assertEquals(19, game.getAllDisks().size());
        // Black
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(5, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(6, 0).getGamePlayer());
        // White
        assertEquals(gamePlayers.get(1), game.getDiskAt(2, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 3).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 4).getGamePlayer());

        // A8
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(1)).setRow(7).setColumn(0));
        assertEquals(16, game.getMoves().size());
        assertEquals(20, game.getAllDisks().size());
        // Black
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(5, 3).getGamePlayer());
        // White
        assertEquals(gamePlayers.get(1), game.getDiskAt(2, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 3).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 4).getGamePlayer());

        // B3
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(0)).setRow(2).setColumn(1));
        assertEquals(17, game.getMoves().size());
        assertEquals(21, game.getAllDisks().size());
        // Black
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 1).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(5, 3).getGamePlayer());
        // White
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 3).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 4).getGamePlayer());

        // E6
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(1)).setRow(5).setColumn(4));
        assertEquals(18, game.getMoves().size());
        assertEquals(22, game.getAllDisks().size());
        // Black
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 1).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 5).getGamePlayer());
        // White
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 3).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 4).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 3).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 4).getGamePlayer());

        // B8
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(0)).setRow(7).setColumn(1));
        assertEquals(19, game.getMoves().size());
        assertEquals(23, game.getAllDisks().size());
        // Black
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 1).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(5, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(6, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(7, 1).getGamePlayer());
        // White
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 4).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 3).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 4).getGamePlayer());

        // B7
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(1)).setRow(6).setColumn(1));
        assertEquals(20, game.getMoves().size());
        assertEquals(24, game.getAllDisks().size());
        // Black
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 1).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(5, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(7, 1).getGamePlayer());
        // White
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 4).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 3).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 4).getGamePlayer());

        // B5
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(0)).setRow(4).setColumn(1));
        assertEquals(21, game.getMoves().size());
        assertEquals(25, game.getAllDisks().size());
        // Black
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 1).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 1).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(5, 1).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(5, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(6, 1).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(7, 1).getGamePlayer());
        // White
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 4).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 3).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 4).getGamePlayer());

        // C8
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(1)).setRow(7).setColumn(2));
        assertEquals(22, game.getMoves().size());
        assertEquals(26, game.getAllDisks().size());
        // Black
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 1).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 1).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(5, 1).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(5, 3).getGamePlayer());
        // White
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 4).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 3).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 4).getGamePlayer());

        // F7
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(0)).setRow(6).setColumn(5));
        assertEquals(23, game.getMoves().size());
        assertEquals(27, game.getAllDisks().size());
        // Black
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 1).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 1).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(5, 1).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(5, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(5, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(6, 5).getGamePlayer());
        // White
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 3).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 4).getGamePlayer());

        // A5
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(1)).setRow(4).setColumn(0));
        assertEquals(24, game.getMoves().size());
        assertEquals(28, game.getAllDisks().size());
        // Black
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 1).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 1).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(5, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(5, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(6, 5).getGamePlayer());
        // White
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 3).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 4).getGamePlayer());

        // D8
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(0)).setRow(7).setColumn(3));
        assertEquals(25, game.getMoves().size());
        assertEquals(29, game.getAllDisks().size());
        // Black
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 1).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 1).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(5, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(5, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(6, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(6, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(7, 3).getGamePlayer());
        // White
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 4).getGamePlayer());

        // A4
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(1)).setRow(3).setColumn(0));
        assertEquals(26, game.getMoves().size());
        assertEquals(30, game.getAllDisks().size());
        // Black
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 1).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(5, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(5, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(6, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(6, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(7, 3).getGamePlayer());
        // White
        assertEquals(gamePlayers.get(1), game.getDiskAt(3, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 4).getGamePlayer());

        // F8
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(0)).setRow(7).setColumn(5));
        assertEquals(27, game.getMoves().size());
        assertEquals(31, game.getAllDisks().size());
        // Black
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 1).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(5, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(5, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(6, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(6, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(7, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(7, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(7, 5).getGamePlayer());
        // White
        assertEquals(gamePlayers.get(1), game.getDiskAt(3, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 2).getGamePlayer());

        // G8
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(1)).setRow(7).setColumn(6));
        assertEquals(28, game.getMoves().size());
        assertEquals(32, game.getAllDisks().size());
        // Black
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 1).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(5, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(5, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(6, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(6, 5).getGamePlayer());
        // White
        assertEquals(gamePlayers.get(1), game.getDiskAt(3, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 3).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 4).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 5).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 6).getGamePlayer());

        // the next move is a skip because black has no valid moves.
        // Any move by black will be skipped.
        move = IOthelloMove.create().setGamePlayer(gamePlayers.get(0)).setRow(7).setColumn(7);
        game.playMove(move);
        assertEquals(29, game.getMoves().size());
        assertEquals(32, game.getAllDisks().size());
        assertEquals(game, move.getGame());
        assertTrue(move.isPassed());

        // Black
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 1).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(2, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(3, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 2).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(4, 5).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(5, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(5, 4).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(6, 3).getGamePlayer());
        assertEquals(gamePlayers.get(0), game.getDiskAt(6, 5).getGamePlayer());
        // White
        assertEquals(gamePlayers.get(1), game.getDiskAt(3, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(4, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(5, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(6, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 0).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 1).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 2).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 3).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 4).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 5).getGamePlayer());
        assertEquals(gamePlayers.get(1), game.getDiskAt(7, 6).getGamePlayer());

        // Now white can play.
        // We fill the board until the end of the game to test it.
        // A2 A3 C2 A1 D2 B1 C1 D1 E1 F1 E2 F2 G1 H1 G2 G3 B2 B4 H2 H3 F4 G4 H4 G5 H5 F6 G6 H6 E7 G7 H7 H8
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(1)).setRow(1).setColumn(0)); // A2
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(0)).setRow(2).setColumn(0)); // A3
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(1)).setRow(2).setColumn(2)); // C2
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(0)).setRow(0).setColumn(0)); // A1
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(1)).setRow(1).setColumn(3)); // D2
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(0)).setRow(0).setColumn(1)); // B1
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(1)).setRow(0).setColumn(2)); // C1
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(0)).setRow(0).setColumn(3)); // D1
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(1)).setRow(0).setColumn(4)); // E1
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(0)).setRow(0).setColumn(5)); // F1
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(1)).setRow(1).setColumn(4)); // E2
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(0)).setRow(1).setColumn(5)); // F2
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(1)).setRow(0).setColumn(6)); // G1
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(0)).setRow(0).setColumn(7)); // H1
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(1)).setRow(1).setColumn(6)); // G2
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(0)).setRow(2).setColumn(6)); // G3
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(1)).setRow(1).setColumn(1)); // B2
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(1)).setRow(3).setColumn(1)); // B4
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(0)).setRow(1).setColumn(7)); // H2
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(1)).setRow(2).setColumn(7)); // H3
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(0)).setRow(3).setColumn(5)); // F4
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(1)).setRow(3).setColumn(6)); // G4
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(0)).setRow(3).setColumn(7)); // H4
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(1)).setRow(4).setColumn(6)); // G5
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(0)).setRow(4).setColumn(7)); // H5
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(1)).setRow(5).setColumn(5)); // F6
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(0)).setRow(5).setColumn(6)); // G6
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(1)).setRow(5).setColumn(7)); // H6
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(0)).setRow(4).setColumn(4)); // E7
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(1)).setRow(6).setColumn(6)); // G7
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(0)).setRow(6).setColumn(7)); // H7
        game.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(1)).setRow(7).setColumn(7)); // H8

        assertTrue(game.isGameOver());
        // Winner is black with 34 over 30.
        assertEquals(gamePlayers.get(0), game.getWinners().get(0));
        assertEquals(34, game.getWinners().get(0).getScore());
        assertEquals(gamePlayers.get(1), game.getLosers().get(0));
        assertEquals(30, game.getLosers().get(0).getScore());
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