package com.pinson.othello.gamePlayers;

import com.pinson.othello.gamePlayers.exceptions.GamePlayerNotFoundException;
import com.pinson.othello.games.OthelloGame;
import com.pinson.othello.players.OthelloPlayer;
import org.springframework.stereotype.Service;

@Service
public class OthelloGamePlayerService {
    private final OthelloGamePlayerRepository othelloGamePlayerRepository;

    public OthelloGamePlayerService(OthelloGamePlayerRepository othelloGamePlayerRepository) {
        this.othelloGamePlayerRepository = othelloGamePlayerRepository;
    }

    public OthelloGamePlayer findByGameAndPlayer(OthelloGame game, OthelloPlayer player) throws GamePlayerNotFoundException {
        return othelloGamePlayerRepository.findByGameAndPlayer(game, player);
    }
}
