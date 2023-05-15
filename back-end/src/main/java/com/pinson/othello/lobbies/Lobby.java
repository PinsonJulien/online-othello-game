package com.pinson.othello.lobbies;

import com.pinson.othello.players.OthelloPlayer;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Lobby implements ILobby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "lobby")
    private List<OthelloPlayer> players;

    @Column(name = "max_players", nullable = false)
    private Integer maxPlayers;

    @Column(name = "created_at", nullable = false)
    private Long createdAt;

    public Lobby() {
        //
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<OthelloPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<OthelloPlayer> players) {
        this.players = players;
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(Integer maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

}
