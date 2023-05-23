package com.pinson.othello.moves;

import com.pinson.othello.gamePlayers.IOthelloGamePlayer;
import com.pinson.othello.gamePlayers.OthelloGamePlayer;
import com.pinson.othello.games.IOthelloGame;
import com.pinson.othello.games.OthelloGame;
import com.pinson.othello.positions.IOthelloPosition;
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
    private Integer row = 0;

    @Column(nullable = false)
    private Integer column = 0;

    private Boolean passed = false;

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
    public Boolean isPassed() {
        return this.passed;
    }

    @Override
    public IOthelloMove setPassed(Boolean passed) {
        this.passed = passed;

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

    @Override
    public IOthelloPosition getPosition() {
        try {
            return IOthelloPosition.create(this.row, this.column);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public IOthelloMove setPosition(Integer row, Integer column) {
        this.setRow(row);
        this.setColumn(column);

        return this;
    }

    @Override
    public IOthelloMove setPosition(IOthelloPosition position) {
        int row = 0;
        int column = 0;

        if (position != null) {
            row = position.getRow();
            column = position.getColumn();
        }

        this.row = row;
        this.column = column;

        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof OthelloMove move))
            return false;

        if (this == move)
            return true;

        return (
            this.getId() == move.getId()
            &&
            this.getGame() == move.getGame()
            &&
            this.getGamePlayer() == move.getGamePlayer()
            &&
            this.getRow().equals(move.getRow())
            &&
            this.getColumn().equals(move.getColumn())
            &&
            this.isPassed().equals(move.isPassed())
            &&
            this.getCreatedAt() == move.getCreatedAt()
        );
    }

    @Override
    public String toString() {

        return (
            "OthelloMove {" +
            "id: " + this.getId() + ", " +
            "game: " + this.getGame() + ", " +
            "gamePlayer: " + this.getGamePlayer() + ", " +
            "row: " + this.getRow() + ", " +
            "column: " + this.getColumn() + ", " +
            "passed: " + this.isPassed() + ", " +
            "createdAt: " + this.getCreatedAt() +
            "}"
        );
    }
}
