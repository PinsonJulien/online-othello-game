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

    @OneToMany(mappedBy = "lobby")
    private List<OthelloPlayer> players;

    @Column(name = "max_players", nullable = false)
    private Integer maxPlayers;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    protected OthelloLobby() {
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
        List<IOthelloPlayer> players = new ArrayList<>();

        for (IOthelloPlayer player : this.players) {
            players.add(player.copy());
        }

        return players;
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
            int playersToRemove = playerSize - maxPlayers;

            for (int i = 0; i < playersToRemove; i++) {
                try {
                    this.removePlayer(this.players.get(playerSize - 1));
                } catch (PlayerNotFoundException exception) {
                    // This should never happen.
                    exception.printStackTrace();
                }
            }
        }

        this.maxPlayers = maxPlayers;

        return this;
    }
}
