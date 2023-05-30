package com.pinson.othello.lobbies;

import com.pinson.othello.ApplicationTestConfiguration;
import com.pinson.othello.commons.exceptions.NonEvenNumberException;
import com.pinson.othello.commons.exceptions.NonPositiveValueException;
import com.pinson.othello.lobbies.exceptions.FullLobbyException;
import com.pinson.othello.lobbies.exceptions.PlayerAlreadyInLobbyException;
import com.pinson.othello.players.IOthelloPlayer;
import com.pinson.othello.players.OthelloPlayer;
import com.pinson.othello.players.OthelloPlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = { ApplicationTestConfiguration.class })
class OthelloLobbyRepositoryTest {

    @Autowired
    private OthelloLobbyRepository lobbyRepository;

    @Autowired
    private OthelloPlayerRepository playerRepository;

    private final List<OthelloLobby> lobbies = new ArrayList<>();
    private final List<IOthelloPlayer> players = new ArrayList<>();

    @BeforeEach
    public void setUp() throws NonPositiveValueException, NonEvenNumberException, FullLobbyException, PlayerAlreadyInLobbyException {
        lobbies.add((OthelloLobby) IOthelloLobby.create(2));
        lobbies.add((OthelloLobby) IOthelloLobby.create(2));
        lobbies.add((OthelloLobby) IOthelloLobby.create(2));
        lobbies.add((OthelloLobby) IOthelloLobby.create(4));
        lobbies.add((OthelloLobby) IOthelloLobby.create(4));
        lobbies.add((OthelloLobby) IOthelloLobby.create(6));
        lobbies.add((OthelloLobby) IOthelloLobby.create(6));

        // Create players
        int nbPlayers = 2 + 1 + 1 + 4 + 3 + 4 + 1;
        for (int i = 0; i < nbPlayers; i++) {
            players.add(IOthelloPlayer.create().setUsername("player" + i).setPassword("password" + i));
        }

        lobbies.get(0).setPlayers(players.subList(0, 2)); // full
        lobbies.get(1).addPlayer(players.get(2)); // 1 / 2 players
        lobbies.get(2).addPlayer(players.get(3)); // 1 / 2 players
        lobbies.get(3).setPlayers(players.subList(3, 7)); // full
        lobbies.get(4).setPlayers(players.subList(7, 10)); // 3 / 4 players
        lobbies.get(5).setPlayers(players.subList(10, 14)); // 4/6 players
        lobbies.get(6).addPlayer(players.get(14)); // 1/6

        lobbyRepository.saveAll(lobbies);
        // Necessary to save players.
        playerRepository.saveAll(players.stream().map(p -> (OthelloPlayer) p).toList());
    }

}