package com.pinson.othello.gamePlayers;

import com.pinson.othello.games.IOthelloGame;
import com.pinson.othello.games.OthelloGame;
import com.pinson.othello.moves.OthelloMove;
import com.pinson.othello.players.IOthelloPlayer;
import com.pinson.othello.players.OthelloPlayer;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class OthelloGamePlayer implements IOthelloGamePlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "othello_game_id", nullable = false)
    private OthelloGame game;

    @ManyToOne
    @JoinColumn(name = "othello_player_id", nullable = false)
    private OthelloPlayer player;

    @OneToMany(mappedBy = "gamePlayer")
    private List<OthelloMove> moves;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OthelloGamePlayerColor playerColor;

    @ManyToMany(mappedBy = "winners")
    private List<OthelloGame> gamesWon;

    @ManyToMany(mappedBy = "losers")
    private List<OthelloGame> gamesLost;

    private Integer score = 0;

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

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public IOthelloGamePlayer setScore(int score) {
        this.score = score;

        return this;
    }

    @Override
    public List<OthelloGame> getGamesWon() {
        return this.gamesWon;
    }

    @Override
    public IOthelloGamePlayer setGamesWon(List<OthelloGame> gamesWon) {
        this.gamesWon = gamesWon;

        return this;
    }

    @Override
    public List<OthelloGame> getGamesLost() {
        return this.gamesLost;
    }

    @Override
    public IOthelloGamePlayer setGamesLost(List<OthelloGame> gamesLost) {
        this.gamesLost = gamesLost;

        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof OthelloGamePlayer gamePlayer)) {
            return false;
        }

        return
            this == gamePlayer
            ||
            this.id.equals(gamePlayer.getId());
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

}
