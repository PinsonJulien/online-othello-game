package com.pinson.othello.games;

import com.pinson.othello.ApplicationTestConfiguration;
import com.pinson.othello.commons.entities.games.exceptions.GameOverException;
import com.pinson.othello.commons.entities.grids.exceptions.GridSizeException;
import com.pinson.othello.commons.exceptions.InvalidMoveException;
import com.pinson.othello.commons.exceptions.InvalidNumberOfPlayersException;
import com.pinson.othello.gamePlayers.IOthelloGamePlayer;
import com.pinson.othello.gamePlayers.OthelloGamePlayer;
import com.pinson.othello.gamePlayers.OthelloGamePlayerColor;
import com.pinson.othello.games.exceptions.CannotPassTurnException;
import com.pinson.othello.games.exceptions.UnknownGamePlayerException;
import com.pinson.othello.moves.IOthelloMove;
import com.pinson.othello.players.IOthelloPlayer;
import com.pinson.othello.positions.IOthelloPosition;
import com.pinson.othello.positions.exceptions.InvalidStandardNotationException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = { ApplicationTestConfiguration.class })
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class OthelloGameServiceTest {
    @Autowired
    private OthelloGameService gameService;

    @Autowired
    private OthelloGameRepository gameRepository;

    private List<OthelloGame> games = new ArrayList<>();
    private List<OthelloGamePlayer> gamePlayers = new ArrayList<>();

    private OthelloGame startedGame;
    private OthelloGame advancedGame;
    private OthelloGame playerSkipGame;
    private OthelloGame finishedGame;

    @BeforeEach
    void setUp() throws GridSizeException, InvalidNumberOfPlayersException, InvalidStandardNotationException, UnknownGamePlayerException, CannotPassTurnException, GameOverException, InvalidMoveException {
        // Generate players
        for (long i = 0L; i < 30; i++) {
            IOthelloPlayer player = IOthelloPlayer.create().setId(i).setUsername("player"+i);
            OthelloGamePlayerColor color = (i%2 == 0) ? OthelloGamePlayerColor.BLACK : OthelloGamePlayerColor.WHITE;
            this.gamePlayers.add((OthelloGamePlayer) IOthelloGamePlayer.create(player, color));
        }

        // Generate games at different states
        // game of 2 players, typical 8x8, just started.
        this.startedGame = (OthelloGame) IOthelloGame.create(gamePlayers.subList(0, 2), 8, 8);
        this.games.add(startedGame);

        // game of 4 players, 10x10, two turned passed, have 8 moves in.
        this.advancedGame = (OthelloGame) IOthelloGame.create(gamePlayers.subList(2, 6), 10, 10);
        // moves : C6 D8 F3 E3 H5 H4 H3 I3
        // additional moves for testing : C7 G8 C5
        this.advancedGame.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(2)).setPosition(IOthelloPosition.create("C6")));
        this.advancedGame.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(3)).setPosition(IOthelloPosition.create("D8")));
        this.advancedGame.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(4)).setPosition(IOthelloPosition.create("F3")));
        this.advancedGame.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(5)).setPosition(IOthelloPosition.create("E3")));
        this.advancedGame.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(2)).setPosition(IOthelloPosition.create("H5")));
        this.advancedGame.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(3)).setPosition(IOthelloPosition.create("H4")));
        this.advancedGame.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(4)).setPosition(IOthelloPosition.create("H3")));
        this.advancedGame.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(5)).setPosition(IOthelloPosition.create("I3")));
        this.games.add(this.advancedGame);

        // game of 2 players, 8x8, one player must pass turn.
        // moves : C4 C3 E6 B4 A4 A5 C2 A3
        this.playerSkipGame = (OthelloGame) IOthelloGame.create(gamePlayers.subList(6, 8), 8, 8);
        this.playerSkipGame.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(6)).setPosition(IOthelloPosition.create("C4")));
        this.playerSkipGame.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(7)).setPosition(IOthelloPosition.create("C3")));
        this.playerSkipGame.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(6)).setPosition(IOthelloPosition.create("E6")));
        this.playerSkipGame.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(7)).setPosition(IOthelloPosition.create("B4")));
        this.playerSkipGame.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(6)).setPosition(IOthelloPosition.create("A4")));
        this.playerSkipGame.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(7)).setPosition(IOthelloPosition.create("A5")));
        this.playerSkipGame.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(6)).setPosition(IOthelloPosition.create("C2")));
        this.playerSkipGame.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(7)).setPosition(IOthelloPosition.create("A3")));

        this.games.add(this.playerSkipGame);

        // game of 2 players, 8x8, finished.
        this.finishedGame = (OthelloGame) IOthelloGame.create(gamePlayers.subList(24, 26), 8, 8);
        // moves: F5 F6 F7 F4 F3 E3 D3 C5 B5
        this.finishedGame.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(24)).setPosition(IOthelloPosition.create("F5")));
        this.finishedGame.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(25)).setPosition(IOthelloPosition.create("F6")));
        this.finishedGame.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(24)).setPosition(IOthelloPosition.create("F7")));
        this.finishedGame.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(25)).setPosition(IOthelloPosition.create("F4")));
        this.finishedGame.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(24)).setPosition(IOthelloPosition.create("F3")));
        this.finishedGame.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(25)).setPosition(IOthelloPosition.create("E3")));
        this.finishedGame.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(24)).setPosition(IOthelloPosition.create("D3")));
        this.finishedGame.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(25)).setPosition(IOthelloPosition.create("C5")));
        this.finishedGame.playMove(IOthelloMove.create().setGamePlayer(gamePlayers.get(24)).setPosition(IOthelloPosition.create("B5")));
        this.games.add(this.finishedGame);

        this.games = this.gameRepository.saveAll(this.games);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void getAllGames() {

    }

    @Test
    void getGameById() {

    }

}
