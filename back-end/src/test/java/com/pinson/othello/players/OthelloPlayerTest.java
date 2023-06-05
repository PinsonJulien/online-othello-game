package com.pinson.othello.players;

import com.pinson.othello.gamePlayers.IOthelloGamePlayer;
import com.pinson.othello.gamePlayers.OthelloGamePlayerColor;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OthelloPlayerTest {

    @Test
    void testEquals() {
        IOthelloPlayer p1 = IOthelloPlayer.create()
                .setId(1L)
                .setUsername("username")
                .setPassword("password");

        IOthelloPlayer p2 = IOthelloPlayer.create()
                .setId(1L)
                .setUsername("username")
                .setPassword("password");

        IOthelloPlayer p3 = IOthelloPlayer.create()
                .setId(2L)
                .setUsername("username")
                .setPassword("password");

        assertEquals(p1, p2);
        assertNotEquals(p1, p3);
        assertNotEquals(p2, null);

        IOthelloGamePlayer gp = IOthelloGamePlayer.create(p1, OthelloGamePlayerColor.BLACK)
                .setId(1L);

        assertNotEquals(p1, gp);
    }

    @Test
    void testHashCode() {
        IOthelloPlayer p1 = IOthelloPlayer.create()
                .setId(1L)
                .setUsername("username")
                .setPassword("password");

        IOthelloPlayer p2 = IOthelloPlayer.create()
                .setId(1L)
                .setUsername("username")
                .setPassword("password");

        IOthelloPlayer p3 = IOthelloPlayer.create()
                .setId(2L)
                .setUsername("username")
                .setPassword("password");

        assertEquals(p1.hashCode(), p2.hashCode());
        assertNotEquals(p1.hashCode(), p3.hashCode());
    }

    // Not a method in OthelloPlayer.
    @Test
    void testSetOfPlayers() {
        IOthelloPlayer p1 = IOthelloPlayer.create()
                .setId(1L)
                .setUsername("username")
                .setPassword("password");

        IOthelloPlayer p2 = IOthelloPlayer.create()
                .setId(1L)
                .setUsername("username")
                .setPassword("password");

        IOthelloPlayer p3 = IOthelloPlayer.create()
                .setId(2L)
                .setUsername("username")
                .setPassword("password");

        Set<IOthelloPlayer> players = new HashSet<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);

        assertEquals(2, players.size());
        assertTrue(players.contains(p1));
        assertTrue(players.contains(p2));
        assertTrue(players.contains(p3));
    }
}