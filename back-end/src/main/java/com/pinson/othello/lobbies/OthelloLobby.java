package com.pinson.othello.lobbies;

import com.pinson.othello.lobbies.exceptions.FullLobbyException;
import com.pinson.othello.lobbies.exceptions.PlayerAlreadyInLobbyException;
import com.pinson.othello.lobbies.exceptions.PlayerNotFoundException;
import com.pinson.othello.players.IOthelloPlayer;
import com.pinson.othello.players.OthelloPlayer;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class OthelloLobby implements IOthelloLobby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "lobby")
    private List<OthelloPlayer> players;

    @Column(name = "max_players", nullable = false)
    private Integer maxPlayers;

    @Column(name = "created_at", nullable = false)
    private Long createdAt;

    public OthelloLobby() {
        //
    }

    @Override
    public IOthelloLobby addPlayer(IOthelloPlayer player) throws PlayerAlreadyInLobbyException, FullLobbyException {
        if (this.isFull())
            throw new FullLobbyException();

        if (this.hasPlayer(player))
            throw new PlayerAlreadyInLobbyException();

        this.players.add((OthelloPlayer) player);

        return this;
    }

    @Override
    public IOthelloLobby removePlayer(IOthelloPlayer player) throws PlayerNotFoundException {
        if (!this.hasPlayer(player))
            throw new PlayerNotFoundException();

        this.players.remove((OthelloPlayer) player);

        return this;
    }

    @Override
    public boolean hasPlayer(IOthelloPlayer player) {
        return this.players.contains((OthelloPlayer) player);
    }

    @Override
    public boolean isFull() {
        return this.players.size() == this.maxPlayers;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public List<IOthelloPlayer> getPlayers() {
        List<OthelloPlayer> players = new ArrayList<>();

        for (IOthelloPlayer player : this.players) {
            players.add(players.clone());
            players.add(player);
        }

        return this.players;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return null;
    }

    @Override
    public IOthelloLobby setCreatedAt() {
        return null;
    }

    @Override
    public Integer getMaxPlayers() {
        return null;
    }

    @Override
    public IOthelloLobby setMaxPlayers() {
        return null;
    }
}
