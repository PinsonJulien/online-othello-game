package com.pinson.othello.players;

import com.pinson.othello.ApplicationTestConfiguration;
import com.pinson.othello.players.exceptions.PlayerNotFoundException;
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
class OthelloPlayerServiceTest {

    @Autowired
    private OthelloPlayerRepository playerRepository;

    @Autowired
    private OthelloPlayerService playerService;

    private List<OthelloPlayer> players = new ArrayList<>();

    @BeforeEach
    void setUp() {
        for (int i = 0; i < 5; i++) {
            this.players.add((OthelloPlayer) IOthelloPlayer.create().setUsername("player"+i).setPassword("pass"));
        }

        this.players = this.playerRepository.saveAll(this.players);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllPlayers() {
        List<OthelloPlayer> result = this.playerService.getAllPlayers();

        assertEquals(5, result.size());
        assertEquals(this.players.get(0).getId(), result.get(0).getId());
        assertEquals(this.players.get(0).getUsername(), result.get(0).getUsername());
        assertEquals(this.players.get(1).getId(), result.get(1).getId());
        assertEquals(this.players.get(1).getUsername(), result.get(1).getUsername());
        assertEquals(this.players.get(2).getId(), result.get(2).getId());
        assertEquals(this.players.get(2).getUsername(), result.get(2).getUsername());
        assertEquals(this.players.get(3).getId(), result.get(3).getId());
        assertEquals(this.players.get(3).getUsername(), result.get(3).getUsername());
        assertEquals(this.players.get(4).getId(), result.get(4).getId());
        assertEquals(this.players.get(4).getUsername(), result.get(4).getUsername());

        OthelloPlayer newPlayer = (OthelloPlayer) IOthelloPlayer.create().setUsername("player5").setPassword("pass");
        this.playerRepository.save(newPlayer);
        result = this.playerService.getAllPlayers();

        assertEquals(6, result.size());
        assertEquals(this.players.get(4).getId() + 1, result.get(5).getId());
        assertEquals("player5", result.get(5).getUsername());

        this.playerRepository.deleteAll();
        result = this.playerService.getAllPlayers();

        assertEquals(0, result.size());
    }

    @Test
    void getPlayerById() {
        OthelloPlayer player = this.playerService.getPlayerById(this.players.get(0).getId());
        assertEquals(this.players.get(0).getUsername(), player.getUsername());

        player = this.playerService.getPlayerById(this.players.get(1).getId());
        assertEquals(this.players.get(1).getUsername(), player.getUsername());

        player = this.playerService.getPlayerById(this.players.get(2).getId());
        assertEquals(this.players.get(2).getUsername(), player.getUsername());

        player = this.playerService.getPlayerById(this.players.get(3).getId());
        assertEquals(this.players.get(3).getUsername(), player.getUsername());

        player = this.playerService.getPlayerById(this.players.get(4).getId());
        assertEquals(this.players.get(4).getUsername(), player.getUsername());

        this.playerRepository.save((OthelloPlayer) IOthelloPlayer.create().setUsername("player6").setPassword("pass"));
        long id = this.playerRepository.findAll().subList(5,6).stream().findFirst().get().getId();
        player = this.playerService.getPlayerById(id);
        assertEquals("player6", player.getUsername());
    }

    @Test
    void getPlayerById__PlayerNotFoundException() {
        assertThrowsExactly(PlayerNotFoundException.class, () -> this.playerService.getPlayerById(0L));
        assertThrowsExactly(PlayerNotFoundException.class, () -> this.playerService.getPlayerById(1932129923L));
        long id = this.players.get(0).getId();
        this.playerRepository.deleteById(id);
        assertThrowsExactly(PlayerNotFoundException.class, () -> this.playerService.getPlayerById(id));
    }
}