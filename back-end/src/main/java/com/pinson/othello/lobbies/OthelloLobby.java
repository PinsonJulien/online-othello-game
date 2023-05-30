package com.pinson.othello.lobbies;

import com.pinson.othello.commons.exceptions.NonEvenNumberException;
import com.pinson.othello.commons.exceptions.NonPositiveValueException;
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

    @OneToMany(mappedBy = "lobby", cascade = CascadeType.ALL)
    private List<OthelloPlayer> players = new ArrayList<>();

    @Column(nullable = false)
    private Integer maxPlayers = 0;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    protected OthelloLobby() {
        //
    }

    @PreRemove
    private void removeLobbyFromPlayers() {
        for (IOthelloPlayer player : players) {
            player.setLobby(null);
        }
    }

    @Override
    public IOthelloLobby addPlayer(IOthelloPlayer player) throws PlayerAlreadyInLobbyException, FullLobbyException {
        if (this.isFull())
            throw new FullLobbyException();

        if (this.hasPlayer(player))
            throw new PlayerAlreadyInLobbyException();

        player.setLobby(this);
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
    public IOthelloLobby setId(Long id) {
        this.id = id;

        return this;
    }

    @Override
    public List<OthelloPlayer> getPlayers() {
        return new ArrayList<>(this.players);
    }

    @Override
    public IOthelloLobby setPlayers(List<IOthelloPlayer> players) throws FullLobbyException, PlayerAlreadyInLobbyException {
        this.players = new ArrayList<>();

        for (IOthelloPlayer player: players) {
            this.addPlayer(player);
        }

        return this;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    @Override
    public IOthelloLobby setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;

        return this;
    }

    @Override
    public Integer getMaxPlayers() {
        return this.maxPlayers;
    }

    @Override
    public IOthelloLobby setMaxPlayers(Integer maxPlayers) throws NonPositiveValueException, NonEvenNumberException {
        if (maxPlayers <= 0)
            throw new NonPositiveValueException("The maximum number of players must be positive.");

        if (maxPlayers%2 != 0 )
            throw new NonEvenNumberException("The maximum number of players must be an even number.");

        if (maxPlayers.equals(this.maxPlayers))
            return this;

        // When we size down we kick the last players of the list.
        int playerSize = this.players.size();
        if (playerSize > maxPlayers) {
            this.players = this.players.subList(0, maxPlayers);
        }

        this.maxPlayers = maxPlayers;

        return this;
    }
}
