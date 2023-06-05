package com.pinson.othello.gamePlayers;

import com.pinson.othello.games.OthelloGame;
import com.pinson.othello.players.OthelloPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface OthelloGamePlayerRepository extends JpaRepository<OthelloGamePlayer, Long> {

    OthelloGamePlayer findByGameAndPlayer(
        @Param("game") OthelloGame game,
        @Param("player") OthelloPlayer player
    );

}
