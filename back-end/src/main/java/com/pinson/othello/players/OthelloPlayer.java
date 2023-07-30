package com.pinson.othello.players;

import com.pinson.othello.commons.entities.players.Player;
import com.pinson.othello.gamePlayers.IOthelloGamePlayer;
import com.pinson.othello.gamePlayers.OthelloGamePlayer;
import com.pinson.othello.lobbies.OthelloLobby;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
public class OthelloPlayer extends Player implements IOthelloPlayer, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "player")
    private List<OthelloGamePlayer> gamePlayers;

    @ManyToMany(mappedBy = "players", cascade = CascadeType.ALL)
    private List<OthelloLobby> lobbies;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public OthelloPlayer() {
        //
    }

    protected OthelloPlayer(IOthelloPlayer player) {
        super();
        this.id = player.getId();
        this.username = player.getUsername();
        this.password = player.getPassword();
        this.createdAt = player.getCreatedAt();
        this.updatedAt = player.getUpdatedAt();
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public IOthelloPlayer setId(Long id) {
        this.id = id;

        return this;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public IOthelloPlayer setUsername(String username) {
        this.username = username;

        return this;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public IOthelloPlayer setPassword(String password) {
        this.password = password;

        return this;
    }

    @Override
    public List<OthelloGamePlayer> getGamePlayers() {
        return this.gamePlayers;
    }

    @Override
    public IOthelloPlayer setGamePlayers(List<IOthelloGamePlayer> gamePlayers) {
        this.gamePlayers = gamePlayers.stream().map(gamePlayer -> (OthelloGamePlayer) gamePlayer).toList();

        return this;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    @Override
    public IOthelloPlayer setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;

        return this;
    }

    @Override
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public IOthelloPlayer setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;

        return this;
    }

    @Override
    public List<OthelloLobby> getLobbies() {
        return this.lobbies;
    }

    @Override
    public IOthelloPlayer copy() {
        return new OthelloPlayer(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof OthelloPlayer player))
            return false;

        return
            this == player
            ||
            this.id == player.getId();
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    // Spring Security methods

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

}
