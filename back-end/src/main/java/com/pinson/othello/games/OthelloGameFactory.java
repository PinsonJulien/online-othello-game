package com.pinson.othello.games;

import com.pinson.othello.commons.entities.grids.exceptions.GridSizeException;
import com.pinson.othello.commons.exceptions.InvalidNumberOfPlayersException;
import com.pinson.othello.gamePlayers.OthelloGamePlayer;
import com.pinson.othello.gamePlayers.OthelloGamePlayerColor;
import com.pinson.othello.players.IOthelloPlayer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Component
public class OthelloGameFactory {

    public OthelloGameFactory() {
        //
    }

    public OthelloGame createClassic(Set<? extends IOthelloPlayer> players) throws InvalidNumberOfPlayersException {
        // Copy players
        List<? extends IOthelloPlayer> copy = new ArrayList<>(players);
        int nbPlayers = copy.size();

        if (nbPlayers != 2)
            throw new InvalidNumberOfPlayersException("Invalid number of players for a classic game (2 expected, " + nbPlayers + " given)");

        // Shuffle players
        Collections.shuffle(copy);

        List<OthelloGamePlayer> gamePlayers = new ArrayList<>(nbPlayers);

        for (int i = 0; i < nbPlayers; i++) {
            IOthelloPlayer player = copy.get(i);
            OthelloGamePlayerColor color = (i%2 == 0)
                ? OthelloGamePlayerColor.BLACK
                : OthelloGamePlayerColor.WHITE;
            gamePlayers.add(new OthelloGamePlayer(player, color));
        }

        try {
            return new OthelloGame(
                gamePlayers,
                8,
                8
            );
        } catch (InvalidNumberOfPlayersException | GridSizeException e) {
            // this should never happen.
            throw new RuntimeException(e);
        }
    }

}
