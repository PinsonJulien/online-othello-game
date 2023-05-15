package com.pinson.othello.gamePlayers;

import com.pinson.othello.games.Game;
import jakarta.persistence.*;

@Entity
@Table(name = "game_players")
public class GamePlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private GamePlayer player;

    @Enumerated(EnumType.STRING)
    @Column(name = "player_color", nullable = false)
    private GamePlayerColor playerColor;

    public GamePlayer() {
        //
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public GamePlayer getPlayer() {
        return player;
    }

    public void setPlayer(GamePlayer player) {
        this.player = player;
    }

    public GamePlayerColor getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(GamePlayerColor playerColor) {
        this.playerColor = playerColor;
    }

}
