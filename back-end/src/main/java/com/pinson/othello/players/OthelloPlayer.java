package com.pinson.othello.players;

import com.pinson.othello.commons.entities.players.Player;
import com.pinson.othello.gamePlayers.OthelloGamePlayer;
import com.pinson.othello.lobbies.OthelloLobby;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Set;

@Entity
public class OthelloPlayer extends Player implements IOthelloPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "player")
    private Set<OthelloGamePlayer> gamePlayers;

    @ManyToOne
    @JoinColumn(name = "lobby_id")
    private OthelloLobby lobby;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private Long createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private Long updatedAt;

    public OthelloPlayer() {
        //
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public Set<OthelloGamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public void setGamePlayers(Set<OthelloGamePlayer> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public IOthelloPlayer clone() {
        return new OthelloPlayer()
                .setId(this.getId())
                .setUsername(this.getUsername())
                .setPassword(this.getPassword())
                .setCreatedAt(this.getCreatedAt())
                .setUpdatedAt(this.getUpdatedAt());
    }

}
