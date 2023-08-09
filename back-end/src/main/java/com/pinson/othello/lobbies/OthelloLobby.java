package com.pinson.othello.lobbies;

import com.pinson.othello.commons.exceptions.NonEvenNumberException;
import com.pinson.othello.commons.exceptions.NonPositiveValueException;
import com.pinson.othello.games.OthelloGame;
import com.pinson.othello.lobbies.exceptions.FullLobbyException;
import com.pinson.othello.lobbies.exceptions.PlayerAlreadyInLobbyException;
import com.pinson.othello.lobbies.exceptions.PlayerNotFoundInLobbyException;
import com.pinson.othello.players.IOthelloPlayer;
import com.pinson.othello.players.OthelloPlayer;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class OthelloLobby implements IOthelloLobby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(targetEntity = OthelloPlayer.class, cascade = CascadeType.ALL)
    private List<OthelloPlayer> players = new ArrayList<>();

    @Column(nullable = false)
    private Integer maxPlayers = 0;

    @OneToOne(mappedBy = "lobby")
    private OthelloGame game;

    @CreatedDate
    private LocalDateTime createdAt = LocalDateTime.now();

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
    public IOthelloLobby removePlayer(IOthelloPlayer player) throws PlayerNotFoundInLobbyException {
        if (!this.hasPlayer(player))
            throw new PlayerNotFoundInLobbyException();

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
    public OthelloGame getGame() {
        return this.game;
    }

    @Override
    public IOthelloLobby setGame(OthelloGame game) {
        this.game = game;
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
