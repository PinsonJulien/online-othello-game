package com.pinson.othello.players;

import com.pinson.othello.ApplicationTestConfiguration;
import com.pinson.othello.players.exceptions.DuplicateUsernameException;
import com.pinson.othello.players.exceptions.PlayerNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    private List<OthelloPlayer> players = new ArrayList<>();

    @BeforeEach
    void setUp() {
        for (int i = 0; i < 5; i++) {
            this.players.add(this.playerService.create("player"+i, "pass"));
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

    @Test
    void getPlayerByUsername() {
        OthelloPlayer player = this.playerService.getPlayerByUsername(this.players.get(0).getUsername());
        assertEquals(this.players.get(0).getId(), player.getId());
        assertEquals(this.players.get(0).getUsername(), player.getUsername());

        player = this.playerService.getPlayerByUsername(this.players.get(1).getUsername());
        assertEquals(this.players.get(1).getId(), player.getId());
        assertEquals(this.players.get(1).getUsername(), player.getUsername());

        player = this.playerService.getPlayerByUsername(this.players.get(2).getUsername());
        assertEquals(this.players.get(2).getId(), player.getId());
        assertEquals(this.players.get(2).getUsername(), player.getUsername());

        player = this.playerService.getPlayerByUsername(this.players.get(3).getUsername());
        assertEquals(this.players.get(3).getId(), player.getId());
        assertEquals(this.players.get(3).getUsername(), player.getUsername());

        player = this.playerService.getPlayerByUsername(this.players.get(4).getUsername());
        assertEquals(this.players.get(4).getId(), player.getId());
        assertEquals(this.players.get(4).getUsername(), player.getUsername());

        this.playerRepository.save((OthelloPlayer) IOthelloPlayer.create().setUsername("player6").setPassword("pass"));
        String username = "player6"; //this.playerRepository.findAll().subList(5,6).stream().findFirst().get().getUsername();
        player = this.playerService.getPlayerByUsername(username);
        assertEquals("player6", player.getUsername());
    }

    @Test
    void getPlayerByUsername__PlayerNotFoundException() {
        assertThrowsExactly(PlayerNotFoundException.class, () -> this.playerService.getPlayerByUsername("testUsername"));
        assertThrowsExactly(PlayerNotFoundException.class, () -> this.playerService.getPlayerByUsername("testUsername2"));
        long id = this.players.get(0).getId();
        String username = this.players.get(0).getUsername();
        this.playerRepository.deleteById(id);
        assertThrowsExactly(PlayerNotFoundException.class, () -> this.playerService.getPlayerByUsername(username));
    }

    @Test
    void create() {
        String username = "player6";
        String password = "pass";

        OthelloPlayer player = this.playerService.create(username, password);
        assertEquals(username, player.getUsername());
        assertEquals(6, this.playerRepository.count());
        assertEquals(6, this.playerService.getAllPlayers().size());
        assertEquals(password, player.getPassword());

        OthelloPlayer repositoryPlayer = this.playerRepository.findById(player.getId()).get();
        assertEquals(username, repositoryPlayer.getUsername());
        assertEquals(player.getPassword(), repositoryPlayer.getPassword());
    }

    @Test
    void create__DuplicateUsernameException() {
        String username = this.players.get(0).getUsername();
        String password = "pass";

        assertEquals(5, this.playerRepository.count());
        assertEquals(5, this.playerService.getAllPlayers().size());
        assertThrowsExactly(DuplicateUsernameException.class, () -> this.playerService.create(username, password));
        assertEquals(5, this.playerRepository.count());
        assertEquals(5, this.playerService.getAllPlayers().size());

        String username2 = "player6";
        OthelloPlayer newPlayer = this.playerService.create(username2, password);
        assertEquals(6, this.playerRepository.count());
        assertEquals(6, this.playerService.getAllPlayers().size());
        assertThrowsExactly(DuplicateUsernameException.class, () -> this.playerService.create(username2, password));
        assertEquals(6, this.playerRepository.count());
        assertEquals(6, this.playerService.getAllPlayers().size());
    }

    @Test
    void updatePassword() {
        OthelloPlayer player = this.players.get(0);
        String password = player.getPassword();
        String newPassword = "newPassword";

        OthelloPlayer updatedPlayer = this.playerService.updatePassword(player.getId(), newPassword);
        assertEquals(player.getId(), updatedPlayer.getId());
        assertEquals(player.getUsername(), updatedPlayer.getUsername());
        assertNotEquals(password, updatedPlayer.getPassword());

        OthelloPlayer repositoryPlayer = this.playerRepository.findById(player.getId()).get();
        assertEquals(player.getId(), repositoryPlayer.getId());
        assertEquals(player.getUsername(), repositoryPlayer.getUsername());
        assertEquals(updatedPlayer.getPassword(), repositoryPlayer.getPassword());
    }

    @Test
    void updatePassword__PlayerNotFoundException() {
        assertThrowsExactly(PlayerNotFoundException.class, () -> this.playerService.updatePassword(0L, "newPassword"));
        assertThrowsExactly(PlayerNotFoundException.class, () -> this.playerService.updatePassword(1932129923L, "newPassword"));
        long id = this.players.get(0).getId();
        this.playerRepository.deleteById(id);
        assertThrowsExactly(PlayerNotFoundException.class, () -> this.playerService.updatePassword(id, "newPassword"));
    }

}