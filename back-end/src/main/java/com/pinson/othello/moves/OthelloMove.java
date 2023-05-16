package com.pinson.othello.moves;

import com.pinson.othello.games.OthelloGame;
import com.pinson.othello.players.OthelloPlayer;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

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
    @JoinColumn(name = "player_id", nullable = false)
    private OthelloPlayer player;

    @Column(name = "x", nullable = false)
    private Integer x;

    @Column(name = "y", nullable = false)
    private Integer y;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private Long createdAt;

    public OthelloMove() {
        //
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public OthelloGame getGame() {
        return game;
    }

    public void setGame(OthelloGame game) {
        this.game = game;
    }

    public OthelloPlayer getPlayer() {
        return player;
    }

    public void setPlayer(OthelloPlayer player) {
        this.player = player;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

}
