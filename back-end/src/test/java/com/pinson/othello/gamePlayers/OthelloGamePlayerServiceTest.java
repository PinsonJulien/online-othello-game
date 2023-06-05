package com.pinson.othello.gamePlayers;

import com.pinson.othello.ApplicationTestConfiguration;
import com.pinson.othello.commons.entities.grids.exceptions.GridSizeException;
import com.pinson.othello.commons.exceptions.InvalidNumberOfPlayersException;
import com.pinson.othello.games.IOthelloGame;
import com.pinson.othello.games.OthelloGame;
import com.pinson.othello.games.OthelloGameRepository;
import com.pinson.othello.moves.IOthelloMove;
import com.pinson.othello.players.IOthelloPlayer;
import com.pinson.othello.players.OthelloPlayer;
import com.pinson.othello.players.OthelloPlayerRepository;
import com.pinson.othello.positions.IOthelloPosition;
import jakarta.transaction.Transactional;
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
class OthelloGamePlayerServiceTest {
    @Autowired
    private OthelloGamePlayerService gamePlayerService;

    @Autowired
    private OthelloPlayerRepository playerRepository;

    @Autowired
    private OthelloGamePlayerRepository gamePlayerRepository;

    @Autowired
    private OthelloGameRepository gameRepository;

    private List<OthelloGamePlayer> gamePlayers = new ArrayList<>();
    private List<OthelloPlayer> players = new ArrayList<>();
    private List<OthelloGame> games = new ArrayList<>();

    @BeforeEach
    void setUp() throws GridSizeException, InvalidNumberOfPlayersException {
        for (int i = 0; i < 30; i++) {
            this.players.add((OthelloPlayer) IOthelloPlayer.create().setUsername("player"+i).setPassword("password"+i));
        }

        this.players = this.playerRepository.saveAll(this.players);

        // Generate game players
        for (int i = 0; i < 12; i++) {
            OthelloGamePlayerColor color = (i%2 == 0) ? OthelloGamePlayerColor.BLACK : OthelloGamePlayerColor.WHITE;
            this.gamePlayers.add((OthelloGamePlayer) IOthelloGamePlayer.create(this.players.get(i), color));
        }

        // generate games
        this.games.add((OthelloGame) IOthelloGame.create(gamePlayers.subList(0, 2), 8, 8));
        this.games.add((OthelloGame) IOthelloGame.create(gamePlayers.subList(2, 4), 8, 8));
        this.games.add((OthelloGame) IOthelloGame.create(gamePlayers.subList(6, 8), 8, 8));
        this.games.add((OthelloGame) IOthelloGame.create(gamePlayers.subList(8, 10), 8, 8));
        this.games.add((OthelloGame) IOthelloGame.create(gamePlayers.subList(10, 12), 8, 8));

        this.games = this.gameRepository.saveAll(this.games);
        this.gamePlayers = this.gamePlayerRepository.findAll();
    }

    @Test
    void findByGameAndPlayer() {
        OthelloGamePlayer gamePlayer = this.gamePlayerRepository.findByGameAndPlayer(this.games.get(0), this.players.get(0));
        assertNotNull(gamePlayer);
        assertEquals(this.gamePlayers.get(0).getGame().getId(), gamePlayer.getGame().getId());
        assertEquals(this.gamePlayers.get(0), gamePlayer);
        assertEquals(this.gamePlayers.get(0).getPlayer(), gamePlayer.getPlayer());
        assertEquals(this.gamePlayers.get(0).getPlayerColor(), gamePlayer.getPlayerColor());

        gamePlayer = this.gamePlayerRepository.findByGameAndPlayer(this.games.get(0), this.players.get(1));
        assertNotNull(gamePlayer);
        assertEquals(this.gamePlayers.get(1).getGame().getId(), gamePlayer.getGame().getId());
        assertEquals(this.gamePlayers.get(1), gamePlayer);
        assertEquals(this.gamePlayers.get(1).getPlayer(), gamePlayer.getPlayer());
        assertEquals(this.gamePlayers.get(1).getPlayerColor(), gamePlayer.getPlayerColor());

        gamePlayer = this.gamePlayerRepository.findByGameAndPlayer(this.games.get(1), this.players.get(2));
        assertNotNull(gamePlayer);
        assertEquals(this.gamePlayers.get(2).getGame().getId(), gamePlayer.getGame().getId());
        assertEquals(this.gamePlayers.get(2), gamePlayer);
        assertEquals(this.gamePlayers.get(2).getPlayer(), gamePlayer.getPlayer());
        assertEquals(this.gamePlayers.get(2).getPlayerColor(), gamePlayer.getPlayerColor());

        gamePlayer = this.gamePlayerRepository.findByGameAndPlayer(this.games.get(1), this.players.get(3));
        assertNotNull(gamePlayer);
        assertEquals(this.gamePlayers.get(3).getGame().getId(), gamePlayer.getGame().getId());
        assertEquals(this.gamePlayers.get(3), gamePlayer);
        assertEquals(this.gamePlayers.get(3).getPlayer(), gamePlayer.getPlayer());
    }
}