package com.pinson.othello.moves;

import com.pinson.othello.gamePlayers.IOthelloGamePlayer;
import com.pinson.othello.gamePlayers.OthelloGamePlayer;
import com.pinson.othello.games.IOthelloGame;
import com.pinson.othello.games.OthelloGame;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "moves")
public class OthelloMove implements IOthelloMove {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private OthelloGame game;

    @ManyToOne
    @JoinColumn(name = "game_player_id", nullable = false)
    private OthelloGamePlayer gamePlayer;

    @Column(nullable = false)
    private Integer row;

    @Column(nullable = false)
    private Integer column;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public OthelloMove() {
        //
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public IOthelloGamePlayer getGamePlayer() {
        return this.gamePlayer;
    }

    @Override
    public IOthelloMove setGamePlayer(IOthelloGamePlayer gamePlayer) {
        this.gamePlayer = (OthelloGamePlayer) gamePlayer;

        return this;
    }

    @Override
    public IOthelloGame getGame() {
        return this.game;
    }

    @Override
    public IOthelloMove setGame(IOthelloGame game) {
        this.game = (OthelloGame) game;

        return this;
    }

    @Override
    public Integer getRow() {
        return this.row;
    }

    @Override
    public IOthelloMove setRow(Integer row) {
        this.row = row;

        return this;
    }

    @Override
    public Integer getColumn() {
        return this.column;
    }

    @Override
    public IOthelloMove setColumn(Integer column) {
        this.column = column;

        return this;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    @Override
    public IOthelloMove setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;

        return this;
    }

}
