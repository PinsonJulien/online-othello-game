package com.pinson.othello.gamePlayers;

import com.pinson.othello.games.Game;
import com.pinson.othello.players.OthelloPlayer;
import jakarta.persistence.*;

@Entity
public class GamePlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "othello_player_id", nullable = false)
    private OthelloPlayer player;

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

    public OthelloPlayer getPlayer() {
        return player;
    }

    public void setPlayer(OthelloPlayer player) {
        this.player = player;
    }

    public GamePlayerColor getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(GamePlayerColor playerColor) {
        this.playerColor = playerColor;
    }

}
