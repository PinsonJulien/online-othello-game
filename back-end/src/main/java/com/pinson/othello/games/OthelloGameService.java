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
import com.pinson.othello.moves.OthelloMoveFactory;
import com.pinson.othello.players.IOthelloPlayer;
import com.pinson.othello.players.OthelloPlayer;
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
    private final OthelloMoveFactory moveFactory;

    @Autowired
    public OthelloGameService(
        final OthelloGameRepository gameRepository,
        final OthelloGameFactory gameFactory,
        final OthelloMoveFactory moveFactory
    ) {
        this.gameRepository = gameRepository;
        this.gameFactory = gameFactory;
        this.moveFactory = moveFactory;
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
        Set<? extends IOthelloPlayer> players = new HashSet<>(lobby.getPlayers());
        OthelloGame game = this.gameFactory.createClassic(players);
        lobby.setGame(game);
        game.setLobby(lobby);

        return this.gameRepository.save(game);
    }

    public OthelloGame playMove(OthelloGame game, OthelloPlayer player, IOthelloPosition position) throws UnknownGamePlayerException, InvalidMoveException, GameOverException {
        // Get the GamePlayer from the game.
        OthelloGamePlayer gamePlayer = game.getGamePlayers().stream()
            .filter(gp -> gp.getPlayer().equals(player))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("The given player is not part of the game"));

        // Create move
        IOthelloMove move = this.moveFactory.create(gamePlayer, position);

        // Play move
        try {
            game.playMove(move);
        } catch (CannotPassTurnException e) {
            // Should not happen.
            e.printStackTrace();
        }

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

    public OthelloGame skipMove(OthelloGame game, OthelloPlayer player) throws GameOverException, CannotPassTurnException, UnknownGamePlayerException, InvalidMoveException {
        // Get the GamePlayer from the game.
        OthelloGamePlayer gamePlayer = game.getGamePlayers().stream()
            .filter(gp -> gp.getPlayer().equals(player))
            .findFirst()
            .orElseThrow(() -> new UnknownGamePlayerException("The given player is not part of the game"));

        // Must be the player's turn.
        if (!gamePlayer.getId().equals(game.getCurrentTurnPlayer().getId()))
            throw new InvalidMoveException("It's not the given player's turn.");

        game.skipMove();

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
