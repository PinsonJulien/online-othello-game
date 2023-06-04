package com.pinson.othello.games;

import com.pinson.othello.commons.entities.games.exceptions.GameOverException;
import com.pinson.othello.commons.entities.grids.exceptions.GridSizeException;
import com.pinson.othello.commons.exceptions.InvalidMoveException;
import com.pinson.othello.commons.exceptions.InvalidNumberOfPlayersException;
import com.pinson.othello.gamePlayers.IOthelloGamePlayer;
import com.pinson.othello.gamePlayers.OthelloGamePlayer;
import com.pinson.othello.gamePlayers.OthelloGamePlayerColor;
import com.pinson.othello.games.exceptions.CannotPassTurnException;
import com.pinson.othello.games.exceptions.GameNotFoundException;
import com.pinson.othello.games.exceptions.UnknownGamePlayerException;
import com.pinson.othello.moves.IOthelloMove;
import com.pinson.othello.players.IOthelloPlayer;
import com.pinson.othello.positions.IOthelloPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class OthelloGameService {
    private final OthelloGameRepository gameRepository;

    @Autowired
    public OthelloGameService(OthelloGameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<OthelloGame> getAllGames() {
        return this.gameRepository.findAll();
    }

    public OthelloGame getGameById(Long id) throws GameNotFoundException {
        return this.gameRepository.findById(id).orElseThrow(() -> new GameNotFoundException(id));
    }

    private OthelloGame startGame(List<OthelloGamePlayer> players, int boardWidth, int boardHeight) throws GridSizeException, InvalidNumberOfPlayersException {
        OthelloGame game = (OthelloGame) IOthelloGame.create(players, boardWidth, boardHeight);
        return this.gameRepository.save(game);
    }

    public OthelloGame startClassicGame(Set<IOthelloPlayer> players) throws InvalidNumberOfPlayersException {
        int size = players.size();

        if (size != 2)
            throw new InvalidNumberOfPlayersException("Invalid number of players for a classic game (2 expected, " + size + " given)");

        // Copy and shuffle players
        List<IOthelloPlayer> playersCopy = new ArrayList<>(players);
        Collections.shuffle(playersCopy);

        // Generate game players
        List<OthelloGamePlayer> gamePlayers = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            IOthelloPlayer player = playersCopy.get(i);
            OthelloGamePlayerColor color = (i%2 == 0)
                ? OthelloGamePlayerColor.BLACK
                : OthelloGamePlayerColor.WHITE;
            gamePlayers.add((OthelloGamePlayer) IOthelloGamePlayer.create(player, color));
        }

        try {
            return this.startGame(gamePlayers, 8, 8);
        } catch (GridSizeException | InvalidNumberOfPlayersException e) {
            throw new RuntimeException(e);
        }
    }

    public OthelloGame playMove(IOthelloGamePlayer gamePlayer, IOthelloPosition position) throws UnknownGamePlayerException, CannotPassTurnException, GameOverException, InvalidMoveException {
        OthelloGame game = (OthelloGame) gamePlayer.getGame();
        IOthelloMove move = IOthelloMove.create().setGamePlayer(gamePlayer).setPosition(position);
        game.playMove(move);

        return this.gameRepository.save(game);
    }
}
