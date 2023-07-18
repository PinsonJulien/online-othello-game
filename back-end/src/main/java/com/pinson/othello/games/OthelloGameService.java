package com.pinson.othello.games;

import com.pinson.othello.commons.entities.games.exceptions.GameOverException;
import com.pinson.othello.commons.entities.grids.exceptions.GridSizeException;
import com.pinson.othello.commons.exceptions.InvalidMoveException;
import com.pinson.othello.commons.exceptions.InvalidNumberOfPlayersException;
import com.pinson.othello.gamePlayers.IOthelloGamePlayer;
import com.pinson.othello.gamePlayers.OthelloGamePlayer;
import com.pinson.othello.games.exceptions.CannotPassTurnException;
import com.pinson.othello.games.exceptions.GameNotFoundException;
import com.pinson.othello.games.exceptions.UnknownGamePlayerException;
import com.pinson.othello.lobbies.OthelloLobby;
import com.pinson.othello.moves.IOthelloMove;
import com.pinson.othello.players.IOthelloPlayer;
import com.pinson.othello.positions.IOthelloPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OthelloGameService {
    private final OthelloGameRepository gameRepository;
    private final OthelloGameFactory gameFactory;

    @Autowired
    public OthelloGameService(
        OthelloGameRepository gameRepository,
        OthelloGameFactory gameFactory
    ) {
        this.gameRepository = gameRepository;
        this.gameFactory = gameFactory;
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

    public OthelloGame startClassicGame(Set<? extends IOthelloPlayer> players) throws InvalidNumberOfPlayersException {
        OthelloGame game = this.gameFactory.createClassic(players);
        return this.gameRepository.save(game);
    }

    public OthelloGame startClassicGame(OthelloLobby lobby) throws InvalidNumberOfPlayersException {
        OthelloGame game = this.gameFactory.createClassic(new HashSet(lobby.getPlayers()));
        game.setLobby(lobby);
        return this.gameRepository.save(game);
    }

    public OthelloGame playMove(IOthelloGamePlayer gamePlayer, IOthelloPosition position) throws UnknownGamePlayerException, InvalidMoveException, GameOverException {
        OthelloGame game = (OthelloGame) gamePlayer.getGame();
        IOthelloMove move = IOthelloMove.create().setGamePlayer(gamePlayer).setPosition(position);

        try {
            game.playMove(move);
        } catch (CannotPassTurnException e) {
            // Should not happen.
            e.printStackTrace();
        }

        return this.gameRepository.save(game);
    }

    public OthelloGame skipMove(IOthelloGamePlayer gamePlayer) throws GameOverException, CannotPassTurnException, UnknownGamePlayerException {
        OthelloGame game = (OthelloGame) gamePlayer.getGame();

        if (!game.getGamePlayers().contains((OthelloGamePlayer) gamePlayer))
            throw new UnknownGamePlayerException("The given game player is not part of the game");

        try {
            game.skipMove();
        } catch (InvalidMoveException e) {
            // Should not happen.
            e.printStackTrace();
        }

        return this.gameRepository.save(game);
    }
}
