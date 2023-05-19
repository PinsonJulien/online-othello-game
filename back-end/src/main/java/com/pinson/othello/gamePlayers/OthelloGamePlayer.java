package com.pinson.othello.gamePlayers;

import com.pinson.othello.games.IOthelloGame;
import com.pinson.othello.games.OthelloGame;
import com.pinson.othello.players.IOthelloPlayer;
import com.pinson.othello.players.OthelloPlayer;
import jakarta.persistence.*;

@Entity
public class OthelloGamePlayer implements IOthelloGamePlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private OthelloGame game;

    @ManyToOne
    @JoinColumn(name = "othello_player_id", nullable = false)
    private OthelloPlayer player;

    @Enumerated(EnumType.STRING)
    @Column(name = "player_color", nullable = false)
    private OthelloGamePlayerColor playerColor;

    public OthelloGamePlayer() {
        //
    }

    public OthelloGamePlayer(IOthelloPlayer player, OthelloGamePlayerColor playerColor) {
        this.setPlayer(player);
        this.setPlayerColor(playerColor);
    }

    public OthelloGamePlayer(IOthelloGame game, OthelloPlayer player, OthelloGamePlayerColor playerColor) {
        this(player, playerColor);

        this.setGame(game);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public IOthelloGamePlayer setId(Long id) {
        this.id = id;

        return this;
    }

    @Override
    public OthelloGame getGame() {
        return game;
    }

    @Override
    public IOthelloGamePlayer setGame(IOthelloGame game) {
        this.game = (OthelloGame) game;

        return this;
    }

    @Override
    public IOthelloPlayer getPlayer() {
        return player;
    }

    @Override
    public IOthelloGamePlayer setPlayer(IOthelloPlayer player) {
        this.player = (OthelloPlayer) player;

        return this;
    }

    @Override
    public OthelloGamePlayerColor getPlayerColor() {
        return playerColor;
    }

    @Override
    public IOthelloGamePlayer setPlayerColor(OthelloGamePlayerColor playerColor) {
        this.playerColor = playerColor;

        return this;
    }

}
