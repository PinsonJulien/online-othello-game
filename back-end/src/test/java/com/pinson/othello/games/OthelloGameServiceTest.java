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
import com.pinson.othello.players.OthelloPlayer;
import com.pinson.othello.positions.IOthelloPosition;
import com.pinson.othello.positions.exceptions.InvalidStandardNotationException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.*;

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
    private List<OthelloPlayer> players = new ArrayList<>();

    private OthelloGame startedGame;
    private OthelloGame advancedGame;
    private OthelloGame playerSkipGame;
    private OthelloGame finishedGame;

    @BeforeEach
    void setUp() throws GridSizeException, InvalidNumberOfPlayersException, InvalidStandardNotationException, UnknownGamePlayerException, CannotPassTurnException, GameOverException, InvalidMoveException {
        // Generate game players
        for (long i = 0L; i < 30L; i++) {
            IOthelloPlayer player = IOthelloPlayer.create().setId(i).setUsername("player"+i);
            OthelloGamePlayerColor color = (i%2 == 0) ? OthelloGamePlayerColor.BLACK : OthelloGamePlayerColor.WHITE;
            this.gamePlayers.add((OthelloGamePlayer) IOthelloGamePlayer.create(player, color));
        }

        // Generate players
        for (long i = 30L; i < 40L; i++) {
            this.players.add((OthelloPlayer) IOthelloPlayer.create().setId(i).setUsername("player"+i));
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
        List<OthelloGame> games = this.gameService.getAllGames();
        assertEquals(4, games.size());
        assertEquals(this.games.get(0).getId(), games.get(0).getId());
        assertEquals(this.games.get(1).getId(), games.get(1).getId());
        assertEquals(this.games.get(2).getId(), games.get(2).getId());
        assertEquals(this.games.get(3).getId(), games.get(3).getId());
    }

    @Test
    void getGameById() {
        OthelloGame game = this.gameService.getGameById(this.startedGame.getId());
        assertEquals(this.startedGame.getId(), game.getId());

        game = this.gameService.getGameById(this.advancedGame.getId());
        assertEquals(this.advancedGame.getId(), game.getId());

        game = this.gameService.getGameById(this.playerSkipGame.getId());
        assertEquals(this.playerSkipGame.getId(), game.getId());

        game = this.gameService.getGameById(this.finishedGame.getId());
        assertEquals(this.finishedGame.getId(), game.getId());
    }

    @Test
    void startClassicGame() throws InvalidNumberOfPlayersException {
        Set<IOthelloPlayer> players = new HashSet<>(this.players.subList(0, 2));
        OthelloGame game = this.gameService.startClassicGame(players);
        assertEquals(8, game.getGridWidth());
        assertEquals(8, game.getGridHeight());
        assertEquals(2, game.getGamePlayers().size());

        // check if the correct players are in the game, considering they're shuffled.
        assertTrue(
            game.getGamePlayers().stream().anyMatch(gamePlayer -> Objects.equals(gamePlayer.getPlayer().getId(), this.players.get(0).getId()))
        );
        assertTrue(
            game.getGamePlayers().stream().anyMatch(gamePlayer -> Objects.equals(gamePlayer.getPlayer().getId(), this.players.get(1).getId()))
        );

        // check if the game is in the repository.
        game = this.gameRepository.findById(game.getId()).orElse(null);
        assertNotNull(game);
        assertEquals(8, game.getGridWidth());
        assertEquals(8, game.getGridHeight());
        assertEquals(2, game.getGamePlayers().size());

        // check if the correct players are in the game, considering they're shuffled.
        assertTrue(
                game.getGamePlayers().stream().anyMatch(gamePlayer -> Objects.equals(gamePlayer.getPlayer().getId(), this.players.get(0).getId()))
        );
        assertTrue(
                game.getGamePlayers().stream().anyMatch(gamePlayer -> Objects.equals(gamePlayer.getPlayer().getId(), this.players.get(1).getId()))
        );
    }

    @Test
    void startClassicGame__InvalidNumberOfPlayersException() {
        Set<IOthelloPlayer> players = new HashSet<>(this.players.subList(0, 1));
        assertThrowsExactly(InvalidNumberOfPlayersException.class, () -> this.gameService.startClassicGame(new HashSet<>(this.players.subList(0, 1))));
        assertThrowsExactly(InvalidNumberOfPlayersException.class, () -> this.gameService.startClassicGame(new HashSet<>(this.players.subList(0, 3))));
        assertThrowsExactly(InvalidNumberOfPlayersException.class, () -> this.gameService.startClassicGame(new HashSet<>(this.players.subList(0, 10))));

        // when there's a given set with two duplicates, it should throw an exception because the set handled the duplication.
        assertThrowsExactly(InvalidNumberOfPlayersException.class, () -> this.gameService.startClassicGame(new HashSet<>(Arrays.asList(this.players.get(0), this.players.get(0)))));
    }

    @Test
    void getGameById__GameNotFoundException() {
        //assertThrowsExactly(GameNotFoundException.class, () -> this.gameService.getGameById(0L));
    }

    @Test
    void playMove__GameNotFoundException() {
        //assertThrowsExactly(GameNotFoundException.class, () -> this.gameService.playMove(0L, IOthelloMove.create().setGamePlayer(this.gamePlayers.get(0)).setPosition(IOthelloPosition.create("A1"))));
    }

    @Test
    void playMoves__GameOverException() {
        //assertThrowsExactly(GameOverException.class, () -> this.gameService.playMove(this.finishedGame.getId(), IOthelloMove.create().setGamePlayer(this.gamePlayers.get(24)).setPosition(IOthelloPosition.create("A1"))));
    }

    @Test
    void playMove__InvalidMoveException() {
        // Players cannot play if it's not their turn

        // Players cannot play if the move isn't possible.
        //assertThrowsExactly(InvalidMoveException.class, () -> this.gameService.playMove(this.startedGame.getId(), IOthelloMove.create().setGamePlayer(this.gamePlayers.get(0)).setPosition(IOthelloPosition.create("A1"))));
    }

    @Test
    void playMove__UnknownGamePlayerException() {
        // The player must be part of the game.
        //        assertThrowsExactly(InvalidMoveException.class, () -> this.gameService.playMove(this.startedGame.getId(), IOthelloMove.create().setGamePlayer(IOthelloGamePlayer.create().setId(0L)).setPosition(IOthelloPosition.create("A1"))));
    }

    @Test
    void skipMove() {

    }

    @Test
    void skipMove__GameNotFoundException() {
        // Players cannot skip if the game doesn't exist.
        //assertThrowsExactly(GameNotFoundException.class, () -> this.gameService.skipMove(0L));
    }

    @Test
    void skipMove__GameOverException() {
        // Players cannot skip if the game is over.
        //assertThrowsExactly(GameOverException.class, () -> this.gameService.skipMove(this.finishedGame.getId()));
    }

    @Test
    void skipMove__CannotPassTurnException() {
        // Players cannot skip if they can play.
    //    assertThrowsExactly(CannotPassTurnException.class, () -> this.gameService.skipMove(this.playerSkipGame.getId()));
    }

    @Test
    void skipMove__InvalidMoveException() {
        // Players cannot play if it's not their turn
    }

    @Test
    void skipMove__UnknownGamePlayerException() {
        // The player must be part of the game.
    }
}
